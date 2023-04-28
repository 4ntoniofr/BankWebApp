package es.taw.grupo25.controller;

import es.taw.grupo25.dto.*;
import es.taw.grupo25.service.*;
import es.taw.grupo25.ui.FiltroClientes;
import es.taw.grupo25.ui.FiltroOperaciones;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CuentaInternaService cuentaInternaService;

    @Autowired
    private EstadoClienteService estadoClienteService;

    @Autowired
    private AutorizacionService autorizacionService;

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @Autowired
    private EstadoCuentaService estadoCuentaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MonedaService monedaService;

    @GetMapping("/")
    public String doMostrarOpciones() {
        return "gestor/index";
    }

    @GetMapping("/login")
    public String doMostrarInicioSesion(HttpSession session) {
        String urlTo = "/gestor/login";

        if (session.getAttribute("usuario") != null) {
            urlTo = "redirect:/gestor/";
        }

        return urlTo;
    }

    @PostMapping("/login")
    public String doLogin(HttpSession session,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model) {

        String urlTo = "redirect:/gestor/";
        Usuario user = this.usuarioService.doAutenticarUsuario(username, password);

        if (user != null && user.getEmpleadosById() != null && user.getEmpleadosById().getRolEmpleadoByRolEmpleadoId().getRol().equals("GESTOR")) {
            session.setAttribute("usuario", user);
        } else {
            model.addAttribute("error", "El usuario o la contraseña no son validos o no corresponden a un gestor");
            urlTo = "/gestor/login";
        }

        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/gestor/";
    }

    @GetMapping("/pendientes")
    public String doMostrarPendientes(Model model) {
        List<Cliente> list = this.clienteService.clientesNoAutorizados();
        separarIndividualesEmpresas(model, list);
        return "gestor/pendientes";
    }

    @GetMapping("/autorizar/{id}")
    public String doAutorizarCliente(@PathVariable("id") Integer idCliente, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }

        Cliente cliente = this.clienteService.findById(idCliente);
        if (cliente == null) {
            return "redirect:/gestor/";
        }
        CuentaInterna cuentaInterna = new CuentaInterna();
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaInterna.setClienteByPropietario(cliente);
        cuentaInterna.setCuentaBancariaByCuentaBancaria(cuentaBancaria);

        List<Moneda> monedas = this.monedaService.findAll();
        model.addAttribute("cuenta", cuentaInterna);
        model.addAttribute("monedas", monedas);

        return "gestor/autorizar";
    }

    @PostMapping("/autorizar")
    public String doAutorizar(@ModelAttribute("cuenta") CuentaInterna cuentaInterna, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }

        Cliente cliente = this.clienteService.findById(cuentaInterna.getClienteByPropietario().getId());
        cuentaInterna.setClienteByPropietario(cliente);
        cuentaInterna.setCantidad(0.0);

        EstadoCuenta estadoCuenta = this.estadoCuentaService.findByEstado("ACTIVA");
        cuentaInterna.setEstadoCuentaByEstadoCuenta(estadoCuenta);

        this.cuentaBancariaService.guardarCuenta(cuentaInterna.getCuentaBancariaByCuentaBancaria());
        this.cuentaInternaService.guardarCuentaNueva(cuentaInterna);

        Empleado gestor = usuario.getEmpleadosById();
        this.clienteService.autorizarCliente(cliente.getId(), gestor.getId());



        return "redirect:/gestor/pendientes";
    }

    @GetMapping("/rechazar/{id}")
    public String doRechazar(@PathVariable("id") Integer idCliente, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        clienteService.borrarCliente(idCliente);
        return "redirect:/gestor/pendientes";
    }

    @GetMapping("/clientes")
    public String doListarClientes(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        List<Cliente> list = this.clienteService.clientesAutorizados();
        separarIndividualesEmpresas(model, list);
        model.addAttribute("filtro", new FiltroClientes());
        model.addAttribute("estados", this.estadoClienteService.findAll());
        return "gestor/clientes";
    }

    private void separarIndividualesEmpresas(Model model, List<Cliente> list) {
        List<Cliente> listPersonas = new ArrayList<>();
        List<Cliente> listEmpresas = new ArrayList<>();
        for (Cliente cliente : list) {
            if (cliente.getPersonaByPersonaId() != null) {
                listPersonas.add(cliente);
            } else {
                listEmpresas.add(cliente);
            }
        }
        model.addAttribute("personas", listPersonas);
        model.addAttribute("empresas", listEmpresas);
    }

    @PostMapping("/filtrarClientes")
    public String doFiltrarClientes(@ModelAttribute("filtro") FiltroClientes filtro, Model model) {
        if (filtro == null || (filtro.getTexto().isEmpty() && filtro.getEstadoCliente().isEmpty())) {
            separarIndividualesEmpresas(model, this.clienteService.clientesAutorizados());
            filtro = new FiltroClientes();
        } else if (filtro.getEstadoCliente().isEmpty()) {
            List<Cliente> listPersonas = this.clienteService.buscarIndividualesPorNombre(filtro.getTexto());
            List<Cliente> listEmpresas = this.clienteService.buscarEmpresaPorNombre(filtro.getTexto());

            model.addAttribute("personas", listPersonas);
            model.addAttribute("empresas", listEmpresas);
        } else if (filtro.getTexto().isEmpty()) {
            separarIndividualesEmpresas(model, this.clienteService.buscarPorEstado(filtro.getEstadoCliente()));
        } else {
            List<Cliente> listPersonas = this.clienteService.buscarIndividualesPorNombreYEstado(filtro.getTexto(), filtro.getEstadoCliente());
            List<Cliente> listEmpresas = this.clienteService.buscarEmpresaPorNombreYEstado(filtro.getTexto(), filtro.getEstadoCliente());

            model.addAttribute("personas", listPersonas);
            model.addAttribute("empresas", listEmpresas);
        }
        model.addAttribute("filtro", filtro);
        model.addAttribute("estados", this.estadoClienteService.findAll());
        return "gestor/clientes";
    }

    @GetMapping("/individual/{id}")
    public String doMostrarDetallesIndividual(@PathVariable("id") Integer idCliente, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        Cliente cliente = this.clienteService.findById(idCliente);
        if (cliente == null) {
            return "gestor/index";
        }
        model.addAttribute("cliente", cliente);
        return "gestor/individual";
    }

    @GetMapping("/empresa/{id}")
    public String doMostrarDetallesEmpresa(@PathVariable("id") Integer idCliente, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        Cliente cliente = this.clienteService.findById(idCliente);
        if (cliente == null) {
            return "gestor/index";
        }
        model.addAttribute("cliente", cliente);
        return "gestor/empresa";
    }

    @GetMapping("/operaciones/{id}")
    public String doMostrarOperacionesCuenta(@PathVariable("id") Integer idCuenta, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        return getTransaccionesYOrdenar(model, idCuenta, "instruccion");
    }

    @PostMapping("/operaciones")
    public String doFiltraroperaciones(@ModelAttribute("filtro") FiltroOperaciones filtro, Model model) {
        if (filtro.getIban().isEmpty() && filtro.getFechaInstruccion().isEmpty() && filtro.getFechaEjecucion().isEmpty()) {
            return getTransaccionesYOrdenar(model, filtro.getIdCuenta(), filtro.getOrden());
        } else {
            CuentaInterna cuenta = this.cuentaInternaService.findById(filtro.getIdCuenta());
            if (cuenta == null) {
                return "gestor/index";
            }
            CuentaBancaria cuentaBancaria = cuenta.getCuentaBancariaByCuentaBancaria();
            List<Transaccion> transacciones = new ArrayList<>();
            transacciones.addAll(cuentaBancaria.getTransaccionsById_Entrantes());
            transacciones.addAll(cuentaBancaria.getTransaccionsById_Salientes());

            if (!filtro.getIban().isEmpty()) {
                transacciones = transacciones.stream().filter(obj -> obj.getCuentaBancariaByCuentaDestino().getIban().contains(filtro.getIban()) || obj.getCuentaBancariaByCuentaOrigen().getIban().contains(filtro.getIban())).collect(Collectors.toList());
            }
            if (!filtro.getFechaInstruccion().isEmpty()) {
                LocalDate fecha = LocalDate.parse(filtro.getFechaInstruccion());
                transacciones = transacciones.stream().filter(obj -> obj.getFechaInstruccion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
            }
            if (!filtro.getFechaEjecucion().isEmpty()) {
                LocalDate fecha = LocalDate.parse(filtro.getFechaEjecucion());
                transacciones = transacciones.stream().filter(obj -> obj.getFechaEjecucion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
            }

            ordenarTransacciones(transacciones, filtro.getOrden());

            model.addAttribute("cuenta", cuentaBancaria);
            model.addAttribute("lista", transacciones);
            model.addAttribute("filtro", filtro);

        }
        model.addAttribute("filtro", filtro);
        model.addAttribute("estados", this.estadoClienteService.findAll());
        return "gestor/operaciones";
    }

    private String getTransaccionesYOrdenar(Model model, int idCuenta, String orden) {
        CuentaInterna cuenta = this.cuentaInternaService.findById(idCuenta);
        if (cuenta == null) {
            return "gestor/index";
        }
        CuentaBancaria cuentaBancaria = cuenta.getCuentaBancariaByCuentaBancaria();
        List<Transaccion> transacciones = new ArrayList<>();
        transacciones.addAll(cuentaBancaria.getTransaccionsById_Entrantes());
        transacciones.addAll(cuentaBancaria.getTransaccionsById_Salientes());
        ordenarTransacciones(transacciones, orden);
        model.addAttribute("cuenta", cuentaBancaria);
        model.addAttribute("lista", transacciones);
        model.addAttribute("filtro", new FiltroOperaciones(cuenta.getId()));
        return "gestor/operaciones";
    }

    private void ordenarTransacciones(List<Transaccion> transacciones, String orden) {
        if (orden.equals("instruccion")) {
            transacciones.sort(new Comparator<Transaccion>() {
                @Override
                public int compare(Transaccion o1, Transaccion o2) {
                    return o1.getFechaInstruccion().compareTo(o2.getFechaInstruccion());
                }
            });
        } else {
            transacciones.sort(new Comparator<Transaccion>() {
                @Override
                public int compare(Transaccion o1, Transaccion o2) {
                    return o1.getFechaEjecucion().compareTo(o2.getFechaEjecucion());
                }
            });
        }
    }

    @GetMapping("/inactivos")
    public String doMostrarInactivos(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        List<Cliente> inactivos = this.clienteService.buscarInactivos(new Date(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000));
        separarIndividualesEmpresas(model, inactivos);
        return "gestor/inactivos";
    }

    @GetMapping("/desactivar/{id}/{urlto}")
    public String doDesactivarCuentas(@PathVariable("id") Integer idCliente, @PathVariable("urlto") String urlTo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        Cliente cliente = this.clienteService.findById(idCliente);
        EstadoCuenta estadoBloqueado = this.estadoCuentaService.findByEstado("BLOQUEADA");
        if (cliente == null) {
            return "gestor/index";
        }
        List<CuentaInterna> cuentas = cliente.getCuentaInternasById();
        for (CuentaInterna cuenta : cuentas) {
            cuenta.setEstadoCuentaByEstadoCuenta(estadoBloqueado);
            this.cuentaInternaService.guardarCuenta(cuenta);
        }
        return "redirect:/gestor/" + urlTo;
    }

    @GetMapping("/sospechosos")
    public String doMostrarClientesSospechosos(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        List<Cliente> sospechosos = this.clienteService.buscarSospechosos();
        separarIndividualesEmpresas(model, sospechosos);
        return "gestor/sospechosos";
    }

    @GetMapping("/solicitudes")
    public String doMostrarSolicitudes(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }
        List<CuentaInterna> cuentasSolicitantes = this.cuentaInternaService.findCuentaInternasSolicitantes();
        model.addAttribute("cuentas", cuentasSolicitantes);
        return "gestor/solicitudes";
    }

    @GetMapping("/desbloquear")
    public String doDesbloquearCuenta(@RequestParam("id") Integer idCuenta, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/gestor/login";
        }

        CuentaInterna cuentaInterna = this.cuentaInternaService.findById(idCuenta);
        if (cuentaInterna == null) {
            return "redirect:/gestor/solicitudes";
        }

        EstadoCuenta estadoCuenta = this.estadoCuentaService.findByEstado("ACTIVA");

        cuentaInterna.setEstadoCuentaByEstadoCuenta(estadoCuenta);
        this.cuentaInternaService.guardarCuenta(cuentaInterna);

        Autorizacion autorizacion = new Autorizacion();
        autorizacion.setFecha(new Date());
        autorizacion.setEmpleadoByEmpleadoId(usuario.getEmpleadosById());
        autorizacion.setCuentaInternaByCuentaInternaId(cuentaInterna);
        this.autorizacionService.guardarAutorizacion(autorizacion);

        return "redirect:/gestor/solicitudes";
    }
}
