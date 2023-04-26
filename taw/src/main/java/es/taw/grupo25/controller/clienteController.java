package es.taw.grupo25.controller;

import es.taw.grupo25.dto.*;
import es.taw.grupo25.entity.ChatEntity;
import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.EmpleadoEntity;
import es.taw.grupo25.entity.MensajeEntity;
import es.taw.grupo25.service.*;
import es.taw.grupo25.ui.FiltroOperaciones;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


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
    public String cliente(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlto = "cliente/cliente";
        if(usuario==null){
            urlto="redirect:/login";
        }else{
            //if(usuario.getClientesById().getEmpleadoByAutorizador()==null){
            //    model.addAttribute("error", "Debe esperar a que un gestor autorize este usuario.");
            //}
        }
        return urlto;
    }

    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session){
        String urlto = "cliente/perfil";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario == null){
            urlto = "redirect:/login";
        }else{
            //if(isAutorizado(usuario)){
                Persona persona = personaService.findById(usuario.getClientesById().getPersonaByPersonaId().getId());
                model.addAttribute("persona", persona);
            //}else{
              //  urlto = "redirect:/cliente";
           // }
        }
        return urlto;
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("persona") Persona persona){
        this.personaService.guardarPersona(persona);
        return "redirect:/cliente";
    }

    @GetMapping("/operaciones")
    public String listarOperaciones(Model model, HttpSession session, @RequestParam("idCuenta")int idCuenta){
        String urlto = "redirect:/cliente/cuentas";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario == null){
            urlto = "redirect:/login";
        }else{
            if(isAutorizado(usuario)){
                CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
                if(cuenta != null){
                    if(cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId()==usuario.getId()){
                        FiltroOperaciones filtro = new FiltroOperaciones(cuenta.getCuentaBancariaByCuentaBancaria().getId());
                        List<Pago> pagos = pagoService.findByCuentaId(cuenta.getCuentaBancariaByCuentaBancaria().getId());
                        model.addAttribute("filtro", filtro);
                        model.addAttribute("pagos", pagos);
                        model.addAttribute("iban", cuenta.getCuentaBancariaByCuentaBancaria().getIban());
                        urlto="cliente/operaciones";
                    }
                }
            }else{
                urlto = "redirect:/cliente";
            }
        }
        return urlto;
    }

    @PostMapping("/operaciones")
    public String aplicarFiltroOperaciones(@ModelAttribute("filtro") FiltroOperaciones filtro ,Model model,HttpSession session){
        String urlTo="/login";
        List<Pago> pagos = pagoService.findByCuentaId(filtro.getIdCuenta());

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            urlTo="cliente/operaciones";
            if(filtro==null || filtro.getFechaEjecucion().isEmpty() &&filtro.getFechaInstruccion().isEmpty() && filtro.getIban().isEmpty()){

            }else{
                if(!filtro.getIban().isEmpty()){
                    pagos = pagos.stream().filter(obj -> obj.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban().contains(filtro.getIban())).collect(Collectors.toList());
                }
                if(!filtro.getFechaInstruccion().isEmpty()){
                    LocalDate fecha = LocalDate.parse(filtro.getFechaInstruccion());
                    pagos = pagos.stream().filter(obj -> obj.getTransaccionByTransaccion().getFechaInstruccion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
                }
                if(!filtro.getFechaEjecucion().isEmpty()){
                    LocalDate fecha = LocalDate.parse(filtro.getFechaEjecucion());
                    pagos = pagos.stream().filter(obj -> obj.getTransaccionByTransaccion().getFechaEjecucion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
                }
            }
            model.addAttribute("pagos", pagos);
        }
        return urlTo;
    }

    @GetMapping("/divisas")
    public String cambiarDivisas(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta){
        String urlTo = "redirect:/cliente/cuentas";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            if(isAutorizado(usuario)){
                CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
                if(cuenta != null){
                    if(cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId()){
                        if(cuenta.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                            model.addAttribute("cuenta", cuenta);
                            model.addAttribute("monedas", monedaService.findAll());
                            model.addAttribute("cambioDivisas", cambioDivisaService.findByCuentaId(cuenta.getCuentaBancariaByCuentaBancaria().getId()));
                            urlTo = "cliente/divisas";
                        }else{
                            model.addAttribute("error", "No se pueden hacer cambios de divisa en cuentas que no esten Activas.");
                            urlTo="/cliente/divisas";
                        }
                    }
                }
            }else{
                urlTo = "redirect:/cliente";
            }
        }
        return urlTo;
    }

    @PostMapping("/divisas")
    public String guardarCambioDivisas(@ModelAttribute("cuenta") CuentaInterna cuenta, Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario==null){
            return "redirect:/login";
        }else{
            CuentaInterna cuenta_cambio = cuentaInternaService.findById(cuenta.getId());
            if(cuenta_cambio!=null){
                if(cuenta_cambio.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId() && cuenta_cambio.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                    Transaccion transaccion = new Transaccion();
                    transaccion.setFechaInstruccion(Timestamp.valueOf(LocalDateTime.now()));
                    transaccion.setFechaEjecucion(Timestamp.valueOf(LocalDateTime.now()));
                    transaccion.setCuentaBancariaByCuentaOrigen(cuenta_cambio.getCuentaBancariaByCuentaBancaria());
                    transaccion.setCuentaBancariaByCuentaDestino(cuenta_cambio.getCuentaBancariaByCuentaBancaria());
                    CambioDivisa cambioDivisa = new CambioDivisa();
                    Moneda moneda_compra = cuenta_cambio.getMonedaByMoneda();
                    cambioDivisa.setMonedaByMonedaCompra(moneda_compra);
                    Moneda moneda_venta = cuenta.getMonedaByMoneda();
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

    private Double getCuentaCantidad(Double cantidad, Moneda moneda_anterior, Moneda moneda_nueva){
        double cantidadEnEuros = cantidad*moneda_anterior.getCambioEuro();
        return cantidadEnEuros/moneda_nueva.getCambioEuro();
    }

    @GetMapping("/transferencia")
    public String hacerTransferencia(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            if(isAutorizado(usuario)){
                CuentaBancaria cuenta = cuentaBancariaService.findById(idCuenta);
                if(cuenta!=null){
                    if(cuenta.getCuentaInternasById().getClienteByPropietario().getUsuarioByUsuarioId().getId()==usuario.getId()){
                        if(cuenta.getCuentaInternasById().getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                            model.addAttribute("pago", new Pago());
                            model.addAttribute("monedas", monedaService.findAll());
                            urlTo = "cliente/transferencia";
                        }else{
                            model.addAttribute("error", "No se pueden hacer transferencias en cuentas que no esten Activas.");
                            urlTo="/cliente/transferencia";
                        }
                    }
                }
            }else{
                urlTo = "redirect:/cliente";
            }
        }
        return urlTo;
    }

    @PostMapping("/transferencia")
    public String guardarTransferencia(@ModelAttribute("pago") Pago pago,@RequestParam("idCuenta") int idCuenta , Model model,HttpSession session){
        CuentaBancaria cuenta = cuentaBancariaService.findById(idCuenta);
        CuentaBancaria cuenta_destino = cuentaBancariaService.findByIban(pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban());
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            if(isCorrect(cuenta, cuenta_destino, pago, model, session, idCuenta)){
                cuenta.getCuentaInternasById().setCantidad(cuenta.getCuentaInternasById().getCantidad()-pago.getCantidad());
                pago.getTransaccionByTransaccion().setCuentaBancariaByCuentaOrigen(cuenta);

                pago.getTransaccionByTransaccion().setFechaInstruccion(Timestamp.valueOf(LocalDateTime.now()));
                pago.getTransaccionByTransaccion().setFechaEjecucion(Timestamp.valueOf(LocalDateTime.now()));

                cuenta_destino.getCuentaInternasById().setCantidad(cuenta_destino.getCuentaInternasById().getCantidad()+pago.getCantidad());
                pago.getTransaccionByTransaccion().setCuentaBancariaByCuentaDestino(cuenta_destino);

                cuentaBancariaService.guardarCuenta(cuenta_destino);
                cuentaBancariaService.guardarCuenta(cuenta);
                transaccionService.guardarTransaccion(pago.getTransaccionByTransaccion());
                pagoService.guardarPago(pago);
            }
        }
        return urlTo;
    }

    /*
        Helper method for post transferencia
     */
    private boolean isCorrect(CuentaBancaria cuenta, CuentaBancaria cuenta_destino, Pago pago, Model model, HttpSession session, Integer idCuenta){
        if(cuenta_destino!=null) {
            if (cuenta.getCuentaInternasById().getCantidad() >= pago.getCantidad()) {
                if (cuenta.getCuentaInternasById().getMonedaByMoneda().equals(cuenta_destino.getCuentaInternasById().getMonedaByMoneda())) {
                    if (cuenta.getCuentaInternasById().getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")) {
                        return true;
                    } else {
                        model.addAttribute("errorTransferencia", "ERROR: La cuenta no está activa.");
                        return false;
                    }
                } else {
                    model.addAttribute("errorTransferencia", "ERROR: Ambas cuentas deben usar la misma divisa.");
                    return false;
                }
            } else {
                model.addAttribute("errorTransferencia", "ERROR: No tienes saldo suficiente");
                return false;
            }
        } else{
            model.addAttribute("errorTransferencia", "ERROR: El iban introducido no existe.");
            return false;
        }
    }

    @GetMapping("/desbloqueo")
    public String intentarDesbloqueo(@RequestParam("idCuenta") int idCuenta ,Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            if(isAutorizado(usuario)){
                CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
                if(cuenta!=null){
                    if(cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId()==usuario.getId()){
                        model.addAttribute("cuenta", cuenta);
                        urlTo = "cliente/desbloqueo";
                    }
                }
            }else{
                urlTo = "redirect:/cliente";
            }
        }
        return urlTo;
    }

    @PostMapping("/desbloqueo")
    public String solicitarDesbloqueo(@ModelAttribute("cuenta") CuentaInterna cuenta, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            CuentaInterna cuenta_cambio = cuentaInternaService.findById(cuenta.getId());
            if(cuenta_cambio!=null){
                if(cuenta_cambio.getClienteByPropietario().getUsuarioByUsuarioId().getId()==usuario.getId()){
                    EstadoCuenta estado = estadoCuentaService.findByEstado("SOLICITADO");
                    cuenta_cambio.setEstadoCuentaByEstadoCuenta(estado);
                    cuentaInternaService.guardarCuenta(cuenta_cambio);
                }
            }
        }
        return urlTo;
    }

    @GetMapping("/register")
    public String registerCliente(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            model.addAttribute("cliente", new Cliente());
            return "cliente/register";
        }else{
            return "redirect:/cliente";
        }
    }

    @PostMapping("/register")
    public String registrar(@ModelAttribute("cliente") Cliente cliente, HttpSession session){
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
    public String listarCuentas(HttpSession session, Model model){
        String urlto = "cliente/cuentas";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario == null){
            urlto = "redirect:/login";
        }else{
            if(isAutorizado(usuario)){
                List<CuentaInterna> cuentas = cuentaInternaService.findByClienteId(usuario.getClientesById().getId());
                List<Moneda> monedas = monedaService.findAll();
                model.addAttribute("cuentas", cuentas);
                model.addAttribute("monedas", monedas);
            }else{
                urlto = "redirect:/cliente";
            }
        }
        return urlto;
    }

    @GetMapping("/nuevaCuenta")
    public String nuevaCuenta(Model model, HttpSession session){
        String urlto = "cliente/cuentas";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario == null) {
            urlto = "redirect:/login";
        }else{
            if(isAutorizado(usuario)){
                model.addAttribute("cuenta", new CuentaInterna());
                model.addAttribute("monedas", monedaService.findAll());
                model.addAttribute("paises", paises);
                urlto = "cliente/nuevaCuenta";
            }else{
                urlto = "redirect:/cliente";
            }
        }
        return urlto;
    }

    @PostMapping("/nuevaCuenta")
    public String aniadirCuenta(@ModelAttribute("cuenta") CuentaInterna cuenta, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        EstadoCuenta estado = estadoCuentaService.findById(1);
        cuenta.setEstadoCuentaByEstadoCuenta(estado);
        cuenta.setClienteByPropietario(usuario.getClientesById());
        cuenta.getCuentaBancariaByCuentaBancaria().setIban(getRandomIban(cuenta.getPais()));

        cuentaBancariaService.guardarCuenta(cuenta.getCuentaBancariaByCuentaBancaria());
        cuentaInternaService.guardarCuenta(cuenta);

        return "redirect:/cliente/cuentas";
    }

    private String getRandomIban(String country){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(getCoutryCode(country));
        for (int i = 0; i < 20; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    private String getCoutryCode(String country){
        return "XX";
    }

    private boolean isAutorizado(Usuario usuario){
        //return usuario.getClientesById().getEmpleadoByAutorizador()!=null;
        return true;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/cliente";
    }



    // Parte de Jorge

    //TODO: cambiar a chat reformateado y eliminar importacion
    @GetMapping("/chat")
    public String chatConAsistencia(HttpSession session, Model model){
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        ChatEntity chat = chatService.findChatAbiertoByClienteId(usuario.getId());

        model.addAttribute("chat", chat);

        //List<MensajeEntity> mensajes = mensajeService.findMensajesByChatId(chat.getId());
        //model.addAttribute("mensajes", mensajes);

        MensajeEntity siguienteMensaje = new MensajeEntity();
        model.addAttribute("siguienteMensaje", siguienteMensaje);

        ClienteEntity cliente = chat.getClienteByClienteId();
        model.addAttribute("cliente", cliente);

        EmpleadoEntity asistente = chat.getEmpleadoByEmpleadoId();
        model.addAttribute("asistente", asistente);

        model.addAttribute("rol", "c");

        return "/asistente/chat";
    }

    @PostMapping("/enviar-mensaje")
    public String doEnviarMensaje(@ModelAttribute("siguienteMensaje") MensajeEntity mensaje) {

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(System.currentTimeMillis());
        mensaje.setFecha(sqlDate);
        mensaje.setLeido(false);
        // this.mensajeService.save(mensaje);

        return "redirect:/cliente/chat";
    }

}