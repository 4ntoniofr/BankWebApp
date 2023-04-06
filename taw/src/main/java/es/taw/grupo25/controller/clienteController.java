package es.taw.grupo25.controller;

import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
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
    private List<String> divisas = Arrays.asList("Euro", "Dollar", "Libra");
    private List<String> paises = Arrays.asList("España", "Estados Unidos", "Inglaterra");

    @Autowired
    private PersonaRepository rep_persona;

    @Autowired
    private UsuarioRepository rep_usuario;

    @Autowired
    private EstadoClienteRepository rep_estado_cliente;

    @Autowired
    private DireccionRepository rep_direccion;

    @Autowired
    private ClienteRepository rep_cliente;

    @Autowired
    private RolClienteRepository rep_rol_cliente;

    @Autowired
    private TransaccionRepository rep_transaccion;

    @Autowired
    private CuentaInternaRepository rep_cuenta_interna;

    @Autowired
    private EstadoCuentaRepository rep_estado_cuenta;

    @Autowired
    private CuentaBancariaRepository rep_cuenta_bancaria;

    @Autowired
    private PagoRepository rep_pago;

    @GetMapping("")
    public String cliente(Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlto = "cliente/cliente";
        if(usuario==null){
            urlto="redirect:/login";
        }
        return urlto;
    }

    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session){
        String urlto = "cliente/perfil";
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if(usuario == null){
            urlto = "redirect:/login";
        }else{
            PersonaEntity persona = rep_persona.findById(usuario.getClientesById().getPersonaByPersonaId().getId()).orElse(null);
            model.addAttribute("persona", persona);
        }
        return urlto;
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("persona") PersonaEntity persona){
        this.rep_persona.save(persona);
        return "redirect:/cliente";
    }

    @GetMapping("/operaciones")
    public String listarOperaciones(Model model, HttpSession session, @RequestParam("idCuenta")int idCuenta){
        String urlto = "redirect:/cliente/cuentas";
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if(usuario == null){
            urlto = "redirect:/login";
        }else{
            CuentaInternaEntity cuenta = rep_cuenta_interna.findById(idCuenta).orElse(null);
            if(cuenta != null){
                if(cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId()==usuario.getId()){
                    FiltroOperaciones filtro = new FiltroOperaciones(cuenta.getCuentaBancariaByCuentaBancaria().getId());
                    List<TransaccionEntity> transacciones =(rep_transaccion.findAllTransactionsById(cuenta.getCuentaBancariaByCuentaBancaria().getId()));model.addAttribute("filtro", filtro);
                    model.addAttribute("transacciones", transacciones);
                    model.addAttribute("filtro", filtro);
                    urlto="cliente/operaciones";
                }
            }
        }
        return urlto;
    }

    @PostMapping("/operaciones")
    public String aplicarFiltroOperaciones(@ModelAttribute("filtro") FiltroOperaciones filtro ,Model model,HttpSession session){
        String urlTo="/login";
        List<TransaccionEntity> transacciones = rep_transaccion.findAllTransactionsById(filtro.getIdCuenta());

        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            urlTo="cliente/operaciones";
            if(filtro==null || filtro.getFechaEjecucion().isEmpty() &&filtro.getFechaInstruccion().isEmpty() && filtro.getIban().isEmpty()){

            }else{
                if(!filtro.getIban().isEmpty()){
                    transacciones = transacciones.stream().filter(obj -> obj.getCuentaBancariaByCuentaDestino().getIban().contains(filtro.getIban())).collect(Collectors.toList());
                }
                if(!filtro.getFechaInstruccion().isEmpty()){
                    LocalDate fecha = LocalDate.parse(filtro.getFechaInstruccion());
                    transacciones = transacciones.stream().filter(obj -> obj.getFechaInstruccion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
                }
                if(!filtro.getFechaEjecucion().isEmpty()){
                    LocalDate fecha = LocalDate.parse(filtro.getFechaEjecucion());
                    transacciones = transacciones.stream().filter(obj -> obj.getFechaEjecucion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
                }
            }
            model.addAttribute("transacciones", transacciones);
        }
        return urlTo;
    }

    @GetMapping("/divisas")
    public String cambiarDivisas(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta){
        String urlTo = "redirect:/cliente/cuentas";
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            CuentaInternaEntity cuenta = rep_cuenta_interna.findById(idCuenta).orElse(null);
            if(cuenta != null){
                if(cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId()){
                    if(cuenta.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                        model.addAttribute("cuenta", cuenta);
                        model.addAttribute("divisas", divisas);
                        urlTo = "cliente/divisas";
                    }else{
                        model.addAttribute("error", "No se pueden hacer cambios de divisa en cuentas que no esten Activas.");
                        urlTo="/cliente/divisas";
                    }
                }
            }
        }
        return urlTo;
    }

    @PostMapping("/divisas")
    public String guardarCambioDivisas(@ModelAttribute("cuenta") CuentaInternaEntity cuenta ,Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if(usuario==null){
            return "redirect:/login";
        }else{
            CuentaInternaEntity cuenta_cambio = rep_cuenta_interna.findById(cuenta.getId()).orElse(null);
            if(cuenta_cambio!=null){
                if(cuenta_cambio.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId() && cuenta_cambio.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                    cuenta_cambio.setMoneda(cuenta.getMoneda());
                    rep_cuenta_interna.save(cuenta_cambio);
                }
            }
        }
        return "redirect:/cliente/cuentas";
    }

    @GetMapping("/transferencia")
    public String hacerTransferencia(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            CuentaBancariaEntity cuenta = rep_cuenta_bancaria.findById(idCuenta).orElse(null);
            if(cuenta!=null){
                if(cuenta.getCuentaInternasById().getClienteByPropietario().getUsuarioByUsuarioId().getId()==usuario.getId()){
                    if(cuenta.getCuentaInternasById().getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                        model.addAttribute("pago", new PagoEntity());
                        model.addAttribute("divisas", divisas);
                        urlTo = "cliente/transferencia";
                    }else{
                        model.addAttribute("error", "No se pueden hacer transferencias en cuentas que no esten Activas.");
                        urlTo="/cliente/transferencia";
                    }
                }
            }
        }
        return urlTo;
    }

    @PostMapping("/transferencia")
    public String guardarTransferencia(@ModelAttribute("pago") PagoEntity pago,@RequestParam("idCuenta") int idCuenta , Model model,HttpSession session){
        CuentaBancariaEntity cuenta = rep_cuenta_bancaria.findById(idCuenta).orElse(null);
        CuentaBancariaEntity cuenta_destino = rep_cuenta_bancaria.findByIban(pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban());
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            if(cuenta_destino!=null){
                if(cuenta.getCuentaInternasById().getCantidad() > pago.getCantidad() && cuenta.getCuentaInternasById().getMoneda().equals(cuenta_destino.getCuentaInternasById().getMoneda())){
                    if(cuenta.getCuentaInternasById().getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                        cuenta.getCuentaInternasById().setCantidad(cuenta.getCuentaInternasById().getCantidad()-pago.getCantidad());
                        pago.getTransaccionByTransaccion().setCuentaBancariaByCuentaOrigen(cuenta);
                        pago.getTransaccionByTransaccion().setFechaInstruccion(Timestamp.valueOf(LocalDateTime.now()));
                        pago.getTransaccionByTransaccion().setFechaEjecucion(Timestamp.valueOf(LocalDateTime.now()));
                        cuenta_destino.getCuentaInternasById().setCantidad(cuenta_destino.getCuentaInternasById().getCantidad()+pago.getCantidad());

                        pago.getTransaccionByTransaccion().setCuentaBancariaByCuentaDestino(cuenta_destino);

                        rep_cuenta_bancaria.save(cuenta_destino);
                        rep_cuenta_bancaria.save(cuenta);
                        rep_transaccion.save(pago.getTransaccionByTransaccion());
                    }
                }
            }else{
                model.addAttribute("errorTransferencia", "ERROR: El iban introducido no existe.");
                return hacerTransferencia(model, session, idCuenta);
            }
        }
        return urlTo;
    }

    @GetMapping("/desbloqueo")
    public String intentarDesbloqueo(@RequestParam("idCuenta") int idCuenta ,Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            CuentaInternaEntity cuenta = rep_cuenta_interna.findById(idCuenta).orElse(null);
            if(cuenta!=null){
                if(cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId()==usuario.getId()){
                    model.addAttribute("cuenta", cuenta);
                    urlTo = "cliente/desbloqueo";
                }
            }
        }
        return urlTo;
    }

    @PostMapping("/desbloqueo")
    public String solicitarDesbloqueo(@ModelAttribute("cuenta") CuentaInternaEntity cuenta, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            urlTo="redirect:/login";
        }else{
            CuentaInternaEntity cuenta_cambio = rep_cuenta_interna.findById(cuenta.getId()).orElse(null);
            if(cuenta_cambio!=null){
                if(cuenta_cambio.getClienteByPropietario().getUsuarioByUsuarioId().getId()==usuario.getId()){
                    EstadoCuentaEntity estado = rep_estado_cuenta.findByEstado("SOLICITADO");
                    cuenta_cambio.setEstadoCuentaByEstadoCuenta(estado);
                    rep_cuenta_interna.save(cuenta_cambio);
                }
            }
        }
        return urlTo;
    }

    @GetMapping("/register")
    public String registerCliente(Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlTo = "redirect:/cliente/cuentas";
        if(usuario==null){
            model.addAttribute("cliente", new ClienteEntity());
            return "cliente/register";
        }else{
            return "redirect:/cliente";
        }
    }

    @PostMapping("/register")
    public String registrar(@ModelAttribute("cliente") ClienteEntity cliente, HttpSession session){
        EstadoClienteEntity estadoCliente = rep_estado_cliente.findByEstado("ACTIVO");
        RolClienteEntity rolCliente = rep_rol_cliente.findByRol("INDIVIDUAL");
        cliente.setFechaInicio(new Date());
        cliente.setEstadoClienteByEstadoCliente(estadoCliente);
        cliente.setRolClienteByRolClienteId(rolCliente);
        cliente.getUsuarioByUsuarioId().setFechaRegistro(new Date());

        rep_usuario.save(cliente.getUsuarioByUsuarioId());
        rep_direccion.save(cliente.getDireccionByDireccion());
        rep_persona.save(cliente.getPersonaByPersonaId());
        rep_cliente.save(cliente);

        return "redirect:/cliente";
    }

    @GetMapping("/cuentas")
    public String listarCuentas(HttpSession session, Model model){
        String urlto = "cliente/cuentas";
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if(usuario == null){
            urlto = "redirect:/login";
        }else{
            List<CuentaInternaEntity> cuentas = (rep_cuenta_interna.findCuentasInternasForClient(usuario.getClientesById().getId()));
            model.addAttribute("cuentas", cuentas);
        }
        return urlto;
    }

    @GetMapping("/nuevaCuenta")
    public String nuevaCuenta(Model model){
        model.addAttribute("cuenta", new CuentaInternaEntity());
        model.addAttribute("divisas", divisas);
        model.addAttribute("paises", paises);
        return "cliente/nuevaCuenta";
    }

    @PostMapping("/nuevaCuenta")
    public String aniadirCuenta(@ModelAttribute("cuenta") CuentaInternaEntity cuenta, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        EstadoCuentaEntity estado = rep_estado_cuenta.findById(1).orElse(null);
        cuenta.setEstadoCuentaByEstadoCuenta(estado);
        cuenta.setClienteByPropietario(usuario.getClientesById());
        cuenta.getCuentaBancariaByCuentaBancaria().setIban(getRandomIban(cuenta.getPais()));
        cuenta.setBloqueada((byte) 0);

        rep_cuenta_bancaria.save(cuenta.getCuentaBancariaByCuentaBancaria());
        rep_cuenta_interna.save(cuenta);

        return "redirect:/cliente/cuentas";
    }

    public String getRandomIban(String country){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(getCoutryCode(country));
        for (int i = 0; i < 20; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public String getCoutryCode(String country){
        return "XX";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/cliente";
    }




}
