package es.taw.grupo25.controller;

import es.taw.grupo25.dto.*;
import es.taw.grupo25.service.*;
import es.taw.grupo25.ui.FiltroChatsAsistente;
import es.taw.grupo25.ui.FiltroOperaciones;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Valentín García Rosas - 80%
 * @author Jorge Camacho García - 20% (Parte de Jorge indicada más adelante en el archivo)
 *
 */

@Controller
@RequestMapping("/cliente")
public class clienteController {
    private List<String> paises = Arrays.asList("España", "Estados Unidos", "Inglaterra");

    @Autowired
    private PersonaService personaService;

    @Autowired
    private CuentaInternaService cuentaInternaService;

    @Autowired
    private MonedaService monedaService;

    @Autowired
    EstadoCuentaService estadoCuentaService;

    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @Autowired
    TransaccionService transaccionService;

    @Autowired
    EstadoClienteService estadoClienteService;

    @Autowired
    PagoService pagoService;

    @Autowired
    RolClienteService rolClienteService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    DireccionService direccionService;
    @Autowired
    CambioDivisaService cambioDivisaService;

    @Autowired
    MensajeService mensajeService;

    @Autowired
    ChatService chatService;

    @GetMapping("")
    public String cliente(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlto = "cliente/cliente";
        if (usuario == null || !usuario.soyCliente()) {
            urlto = "redirect:/login";
        } else {
            if (!usuario.getClientesById().getAutorizador()) {
                model.addAttribute("error", "Debe esperar a que un gestor autorize este usuario.");
            }
        }
        return urlto;
    }

    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session) {
        String urlto = "cliente/perfil";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.soyCliente()) {
            urlto = "redirect:/login";
        } else {
            if (usuario.getClientesById().getAutorizador()) {
                Cliente cliente = clienteService.findById(usuario.getClientesById().getId());
                model.addAttribute("cliente", cliente);
            } else {
                urlto = "redirect:/cliente";
            }
        }
        return urlto;
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        direccionService.saveDireccion(cliente.getDireccionByDireccion());
        personaService.guardarPersona(cliente.getPersonaByPersonaId());
        return "redirect:/cliente";
    }

    @GetMapping("/operaciones")
    public String listarOperaciones(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta) {
        String urlto = "redirect:/cliente/cuentas";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.soyCliente()) {
            urlto = "redirect:/login";
        } else {
            CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
            if (comprobacionCuentas(usuario, cuenta)) {
                FiltroOperaciones filtro = new FiltroOperaciones(cuenta.getCuentaBancariaByCuentaBancaria().getId());
                List<Pago> pagos = pagoService.findByCuentaId(cuenta.getCuentaBancariaByCuentaBancaria().getId());
                model.addAttribute("filtro", filtro);
                model.addAttribute("pagos", pagos);
                model.addAttribute("idCuenta", idCuenta);
                model.addAttribute("iban", cuenta.getCuentaBancariaByCuentaBancaria().getIban());
                urlto = "cliente/operaciones";
            }
        }
        return urlto;
    }

    @PostMapping("/operaciones")
    public String aplicarFiltroOperaciones(@ModelAttribute("filtro") FiltroOperaciones filtro, Model model, HttpSession session) {
        String urlTo = "/login";
        List<Pago> pagos = pagoService.findByCuentaId(filtro.getIdCuenta());
        CuentaBancaria cuenta = cuentaBancariaService.findById(filtro.getIdCuenta());
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.soyCliente()) {
            urlTo = "redirect:/login";
        } else {
            urlTo = "cliente/operaciones";
            if (filtro == null || filtro.getFechaEjecucion().isEmpty() && filtro.getFechaInstruccion().isEmpty() && filtro.getIban().isEmpty() && filtro.getIbanOrigen().isEmpty() && filtro.getPagoingreso().isEmpty()) {

            } else {
                if (!filtro.getIban().isEmpty()) {
                    pagos = pagos.stream().filter(obj -> obj.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban().contains(filtro.getIban())).collect(Collectors.toList());
                }
                if(!filtro.getIbanOrigen().isEmpty()){
                    pagos = pagos.stream().filter(pago -> pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaOrigen().getIban().contains(filtro.getIbanOrigen())).collect(Collectors.toList());
                }
                if(!filtro.getPagoingreso().isEmpty()){
                    if(filtro.getPagoingreso().equals("pago")){
                        pagos = pagos.stream().filter(pago -> pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaOrigen().getIban().equals(cuenta.getIban())).collect(Collectors.toList());
                    }else if(filtro.getPagoingreso().equals("ingreso")){
                        pagos = pagos.stream().filter(pago -> pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban().equals(cuenta.getIban())).collect(Collectors.toList());
                    }
                }
                if (!filtro.getFechaInstruccion().isEmpty()) {
                    LocalDate fecha = LocalDate.parse(filtro.getFechaInstruccion());
                    pagos = pagos.stream().filter(obj -> obj.getTransaccionByTransaccion().getFechaInstruccion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
                }
                if (!filtro.getFechaEjecucion().isEmpty()) {
                    LocalDate fecha = LocalDate.parse(filtro.getFechaEjecucion());
                    pagos = pagos.stream().filter(obj -> obj.getTransaccionByTransaccion().getFechaEjecucion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
                }
            }
            ordenarTransacciones(pagos, filtro.getOrden(), filtro.getAscdesc());
            model.addAttribute("iban", cuenta.getIban());
            model.addAttribute("pagos", pagos);
        }
        return urlTo;
    }

    private void ordenarTransacciones(List<Pago> pagos, String orden, String ascdesc) {
        if(orden.equals("instruccion") && ascdesc.equals("ascendente")){
            System.out.println("asc");
            pagos.sort((o1, o2) -> o1.getTransaccionByTransaccion().getFechaInstruccion().compareTo(o2.getTransaccionByTransaccion().getFechaInstruccion()));
        }else if(orden.equals("instruccion") && ascdesc.equals("descendente")){
            System.out.println("desc");
            pagos.sort((o1, o2) -> o2.getTransaccionByTransaccion().getFechaInstruccion().compareTo(o1.getTransaccionByTransaccion().getFechaInstruccion()));
        }else if(orden.equals("ejecucion") && ascdesc.equals("ascendente")){
            pagos.sort((o1, o2) -> o1.getTransaccionByTransaccion().getFechaEjecucion().compareTo(o2.getTransaccionByTransaccion().getFechaEjecucion()));
        }else{
            pagos.sort((o1, o2) -> o2.getTransaccionByTransaccion().getFechaEjecucion().compareTo(o1.getTransaccionByTransaccion().getFechaEjecucion()));
        }
    }

    @PostMapping("borrarFiltro")
    public String borraFiltro(@ModelAttribute("filtro") FiltroOperaciones filtro){
        return "redirect:/cliente/operaciones?idCuenta="+filtro.getIdCuenta();
    }

    @GetMapping("/divisas")
    public String cambiarDivisas(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta) {
        String urlTo = "redirect:/cliente/cuentas";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.soyCliente()) {
            urlTo = "redirect:/login";
        } else {
            CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
            if (comprobacionCuentas(usuario, cuenta)) {
                if (cuenta.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")) {
                    model.addAttribute("cuenta", cuenta);
                    model.addAttribute("monedas", monedaService.findAll());
                    model.addAttribute("cambioDivisas", cambioDivisaService.findByCuentaId(cuenta.getCuentaBancariaByCuentaBancaria().getId()));
                    urlTo = "cliente/divisas";
                } else {
                    model.addAttribute("error", "No se pueden hacer cambios de divisa en cuentas que no esten Activas.");
                    urlTo = "/cliente/divisas";
                }
            }
        }
        return urlTo;
    }

    @PostMapping("/divisas")
    public String guardarCambioDivisas(@ModelAttribute("cuenta") CuentaInterna cuenta, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.soyCliente()) {
            return "redirect:/login";
        } else {
            CuentaInterna cuenta_cambio = cuentaInternaService.findById(cuenta.getId());
                if(comprobacionCuentas(usuario, cuenta_cambio)){
                    if (cuenta_cambio.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")) {
                        Transaccion transaccion = new Transaccion();
                        transaccion.setFechaInstruccion(Timestamp.valueOf(LocalDateTime.now()));
                        transaccion.setFechaEjecucion(Timestamp.valueOf(LocalDateTime.now()));
                        transaccion.setCuentaBancariaByCuentaOrigen(cuenta_cambio.getCuentaBancariaByCuentaBancaria());
                        transaccion.setCuentaBancariaByCuentaDestino(cuenta_cambio.getCuentaBancariaByCuentaBancaria());
                        CambioDivisa cambioDivisa = new CambioDivisa();
                        Moneda moneda_compra = monedaService.findById(cuenta_cambio.getMonedaByMoneda());
                        cambioDivisa.setMonedaByMonedaCompra(moneda_compra);
                        Moneda moneda_venta = monedaService.findById(cuenta.getMonedaByMoneda());
                        cambioDivisa.setMonedaByMonedaVenta(moneda_venta);
                        cambioDivisa.setTransaccionByTransaccion(transaccion);
                        cuenta_cambio.setMonedaByMoneda(cuenta.getMonedaByMoneda());
                        cuenta_cambio.setCantidad(getCuentaCantidad(cuenta_cambio.getCantidad(), moneda_compra, moneda_venta));
                        transaccionService.guardarTransaccion(transaccion);
                        cambioDivisaService.guardarCambioDivisa(cambioDivisa);
                        cuentaInternaService.guardarCuenta(cuenta_cambio);
                }
            }
        }
        return "redirect:/cliente/cuentas";
    }

    /**
     * Función para obtener la nueva cantidad que se guarda en la cuenta cuando se hace un cambio de divisa
     *
     * @param cantidad
     * @param moneda_anterior
     * @param moneda_nueva
     * @return double
     */
    private Double getCuentaCantidad(Double cantidad, Moneda moneda_anterior, Moneda moneda_nueva) {
        double cantidadEnEuros = cantidad * moneda_anterior.getCambioEuro();
        return cantidadEnEuros / moneda_nueva.getCambioEuro();
    }

    @GetMapping("/transferencia")
    public String hacerTransferencia(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if (usuario == null || !usuario.soyCliente()) {
            urlTo = "redirect:/login";
        } else {
            CuentaBancaria cuenta = cuentaBancariaService.findById(idCuenta);
            if (comprobacionCuentas(usuario, cuenta.getCuentaInternasById().toDTO())) {
                if (cuenta.getCuentaInternasById().getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")) {
                    model.addAttribute("pago", new Pago());
                    model.addAttribute("monedas", monedaService.findAll());
                    urlTo = "cliente/transferencia";
                } else {
                    model.addAttribute("error", "No se pueden hacer transferencias en cuentas que no esten Activas.");
                    urlTo = "/cliente/transferencia";
                }
            }
        }
        return urlTo;
    }

    @PostMapping("/transferencia")
    public String guardarTransferencia(@ModelAttribute("pago") Pago pago, @RequestParam("idCuenta") int idCuenta, Model model, HttpSession session) {
        CuentaBancaria cuenta = cuentaBancariaService.findById(idCuenta);
        CuentaBancaria cuenta_destino = cuentaBancariaService.findByIban(pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban());
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if (usuario == null || !usuario.soyCliente()) {
            urlTo = "redirect:/login";
        } else {
            if (isCorrect(cuenta, cuenta_destino, pago, model, session, idCuenta)) {
                cuenta.getCuentaInternasById().setCantidad(cuenta.getCuentaInternasById().getCantidad() - pago.getCantidad());
                pago.getTransaccionByTransaccion().setCuentaBancariaByCuentaOrigen(cuenta);

                pago.getTransaccionByTransaccion().setFechaInstruccion(Timestamp.valueOf(LocalDateTime.now()));
                pago.getTransaccionByTransaccion().setFechaEjecucion(Timestamp.valueOf(LocalDateTime.now()));

                cuenta_destino.getCuentaInternasById().setCantidad(cuenta_destino.getCuentaInternasById().getCantidad() + pago.getCantidad());
                pago.getTransaccionByTransaccion().setCuentaBancariaByCuentaDestino(cuenta_destino);

                cuentaBancariaService.guardarCuenta(cuenta_destino);
                cuentaBancariaService.guardarCuenta(cuenta);
                transaccionService.guardarTransaccion(pago.getTransaccionByTransaccion());
                pagoService.guardarPago(pago);
            }else{
                urlTo = "cliente/transferencia";
            }
        }
        return urlTo;
    }

    /**
     *  Función auxiliar para hacer una transferencia
     */
    private boolean isCorrect(CuentaBancaria cuenta, CuentaBancaria cuenta_destino, Pago pago, Model model, HttpSession session, Integer idCuenta) {
        if (cuenta_destino != null) {
            if (cuenta.getCuentaInternasById().getCantidad() >= pago.getCantidad()) {
                if (cuenta.getCuentaInternasById().getMonedaByMoneda().equals(cuenta_destino.getCuentaInternasById().getMonedaByMoneda())) {
                    if (cuenta.getCuentaInternasById().getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")) {
                        return true;
                    } else {
                        model.addAttribute("errorTransferencia", "ERROR: La cuenta no está activa.");
                    }
                } else {
                    model.addAttribute("errorTransferencia", "ERROR: Ambas cuentas deben usar la misma divisa.");
                }
            } else {
                model.addAttribute("errorTransferencia", "ERROR: No tienes saldo suficiente");
            }
        } else {
            model.addAttribute("errorTransferencia", "ERROR: El iban introducido no existe.");
        }
        return false;
    }

    @GetMapping("/desbloqueo")
    public String intentarDesbloqueo(@RequestParam("idCuenta") int idCuenta, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if (usuario == null || !usuario.soyCliente()) {
            urlTo = "redirect:/login";
        } else {
            CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
            if (comprobacionCuentas(usuario, cuenta)) {
                model.addAttribute("cuenta", cuenta);
                urlTo = "cliente/desbloqueo";
            }
        }
        return urlTo;
    }

    @PostMapping("/desbloqueo")
    public String solicitarDesbloqueo(@ModelAttribute("cuenta") CuentaInterna cuenta, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if (usuario == null || !usuario.soyCliente()) {
            urlTo = "redirect:/login";
        } else {
            CuentaInterna cuenta_cambio = cuentaInternaService.findById(cuenta.getId());
                if (comprobacionCuentas(usuario, cuenta_cambio)) {
                    EstadoCuenta estado = estadoCuentaService.findByEstado("SOLICITADO");
                    cuenta_cambio.setEstadoCuentaByEstadoCuenta(estado);
                    cuentaInternaService.guardarCuenta(cuenta_cambio);
                }
        }
        return urlTo;
    }

    @GetMapping("/register")
    public String registerCliente(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if (usuario == null || !usuario.soyCliente()) {
            model.addAttribute("cliente", new Cliente());
            return "cliente/register";
        } else {
            return "redirect:/cliente";
        }
    }

    @PostMapping("/register")
    public String registrar(@ModelAttribute("cliente") Cliente cliente, HttpSession session) {
        EstadoCliente estadoCliente = estadoClienteService.findByEstado("ACTIVO");
        RolCliente rolCliente = rolClienteService.findByRol("INDIVIDUAL");
        cliente.setFechaInicio(new Date());
        cliente.setEstadoClienteByEstadoCliente(estadoCliente);
        cliente.setRolClienteByRolClienteId(rolCliente);
        cliente.getUsuarioByUsuarioId().setFechaRegistro(new Date());


        usuarioService.guardarUsuario(cliente.getUsuarioByUsuarioId());
        direccionService.saveDireccion(cliente.getDireccionByDireccion());
        personaService.guardarPersona(cliente.getPersonaByPersonaId());
        clienteService.guardarCliente(cliente);

        return "redirect:/cliente";
    }

    @GetMapping("/cuentas")
    public String listarCuentas(HttpSession session, Model model) {
        String urlto = "cliente/cuentas";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.soyCliente()) {
            urlto = "redirect:/login";
        } else {
            if (usuario.getClientesById().getAutorizador()) {
                List<CuentaInterna> cuentas = cuentaInternaService.findByClienteId(usuario.getClientesById().getId());
                List<CuentaInterna> cuentas_empresa = null;
                if(usuario.getClientesById().getEmpresaByEmpresaSocio()!=null){
                    cuentas_empresa = cuentaInternaService.findByClienteId(usuario.getClientesById().getEmpresaByEmpresaSocio().getClienteByClienteId().getId());
                }
                List<Moneda> monedas = monedaService.findAll();
                model.addAttribute("cuentas", cuentas);
                model.addAttribute("cuentas_empresa", cuentas_empresa);
                model.addAttribute("monedas", monedas);
            } else {
                urlto = "redirect:/cliente";
            }
        }
        return urlto;
    }

    @GetMapping("/nuevaCuenta")
    public String nuevaCuenta(Model model, HttpSession session) {
        String urlto = "cliente/cuentas";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.soyCliente()) {
            urlto = "redirect:/login";
        } else {
            if (usuario.getClientesById().getAutorizador()) {
                model.addAttribute("cuenta", new CuentaInterna());
                model.addAttribute("monedas", monedaService.findAll());
                model.addAttribute("paises", paises);
                urlto = "cliente/nuevaCuenta";
            } else {
                urlto = "redirect:/cliente";
            }
        }
        return urlto;
    }

    @PostMapping("/nuevaCuenta")
    public String aniadirCuenta(@ModelAttribute("cuenta") CuentaInterna cuenta, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        EstadoCuenta estado = estadoCuentaService.findById(1);
        cuenta.setEstadoCuentaByEstadoCuenta(estado);
        cuenta.setClienteByPropietario(usuario.getClientesById());
        cuenta.getCuentaBancariaByCuentaBancaria().setIban(getRandomIban(cuenta.getPais()));

        cuentaBancariaService.guardarCuenta(cuenta.getCuentaBancariaByCuentaBancaria());
        cuentaInternaService.guardarCuenta(cuenta);

        return "redirect:/cliente/cuentas";
    }

    /**
     * Función auxiliar para hacer comprobaciones de cuenta en varias funciones.
     *
     * @param usuario
     * @param cuenta
     * @return boolean
     */
    private boolean comprobacionCuentas(Usuario usuario, CuentaInterna cuenta){
        // comprobamos que tenga autorizador, y por tanto la cuenta está habilitada para hacer cosas.
        if(usuario.getClientesById().getAutorizador()){
            // comprobamos que exista la cuenta con la que queremos hacer operaciones (en caso de que no se redireccionara a las cuentas)
            if(cuenta!=null){
                Empresa empresaAsociada = usuario.getClientesById().getEmpresaByEmpresaSocio();
                // El id de la cuenta tiene que ser del cliente o de la empresa de la que es socio para poder mostrarse
                if(cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId() || (empresaAsociada!=null && cuenta.getClienteByPropietario().getId() == empresaAsociada.getClienteByClienteId().getId())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  Función auxiliar para obtener un IBAN
     */
    private String getRandomIban(String country) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(getCoutryCode(country));
        for (int i = 0; i < 20; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    /**
     *  TODO (no es necesario hacerlo)
     *  Función auxiliar para conseguir el codigo de un pais para el IBAN
     */
    private String getCoutryCode(String country) {
        return "XX";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    // Parte de Jorge

    @GetMapping("/chats")
    public String listadoDeChats(Model model, HttpSession session){
        FiltroChatsAsistente filtro = new FiltroChatsAsistente();
        filtro.setOrden(1); // Primero los chats más recientes
        return this.procesarFiltradoCliente(model, session, filtro);
    }

    private String procesarFiltradoCliente(Model model, HttpSession session, FiltroChatsAsistente filtro){
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || usuario.getClientesById() == null) {
            return "redirect:/login";
        }

        Cliente clienteQueSoy = usuario.getClientesById();

        List<Chat> chatsAbiertos = chatService.findChatsAbiertosByClienteId(clienteQueSoy.getId());

        List<Chat> chatsCerrados = chatService.findChatsCerradosByClienteId(clienteQueSoy.getId());

        if(filtro != null){
            chatsAbiertos.sort(Comparator.comparing(Chat::getId));
            chatsCerrados.sort(Comparator.comparing(Chat::getId));

            if(filtro.getOrden() == 1){
                Collections.reverse(chatsAbiertos);
                Collections.reverse(chatsCerrados);
            }
        }else{
            filtro = new FiltroChatsAsistente();
            filtro.setOrden(1);
        }

        model.addAttribute("chatsAbiertos", chatsAbiertos);
        model.addAttribute("chatsCerrados", chatsCerrados);
        model.addAttribute("filtro", filtro);

        return "/cliente/chats";
    }

    @PostMapping("/chats")
    public String listadoDeChatsFiltrado(Model model, HttpSession session, @ModelAttribute("filtro") FiltroChatsAsistente filtro){
        return this.procesarFiltradoCliente(model, session, filtro);
    }





    @GetMapping("/chat")
    public String chatConAsistencia(Model model, @RequestParam("id") Integer chatId, HttpSession session) {
        Chat chat = chatService.findById(chatId);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || chat == null || usuario.getClientesById() == null || chat.getClienteByClienteId().getId() != usuario.getClientesById().getId()) {
            return "redirect:/login";
        }
        Cliente clienteQueSoy = usuario.getClientesById();
        model.addAttribute("cliente", clienteQueSoy);

        model.addAttribute("chat", chat);

        List<Mensaje> mensajes = chat.getMensajesById();
        model.addAttribute("mensajes", mensajes);

        Mensaje siguienteMensaje = new Mensaje();
        model.addAttribute("siguienteMensaje", siguienteMensaje);

        Empleado asistente = chat.getEmpleadoByEmpleadoId();
        model.addAttribute("asistente", asistente);

        model.addAttribute("rol", "c");

        return "/asistente/chat";
    }

    @PostMapping("/enviar-mensaje")
    public String doEnviarMensaje(@ModelAttribute("siguienteMensaje") Mensaje mensaje) {
        mensajeService.enviarMensaje(mensaje);
        return "redirect:/cliente/chat?id=" + mensaje.getIdChatNuevoMensaje();
    }

    @PostMapping("/cerrar-chat")
    public String doCerrarChat(@RequestParam("id") Integer chatId) {
        Chat chat = chatService.findById(chatId);
        chatService.cerrarChat(chat);

        return "redirect:/cliente/chats";
    }

    @PostMapping("/nuevoChat")
    public String generarNuevoChat(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || usuario.getClientesById() == null) {
            return "redirect:/login";
        }

        Cliente clienteQueSoy = usuario.getClientesById();
        Chat nuevoChat = chatService.generarNuevoChatConAsistenteAleatorio(clienteQueSoy);

        if(nuevoChat != null){
            return "redirect:/cliente/chat?id=" + nuevoChat.getId();
        }else{
            return "redirect:/cliente/chats";
        }


    }

}