package es.taw.grupo25.controller;

import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import es.taw.grupo25.ui.FiltroClientes;
import es.taw.grupo25.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaInternaRepository cuentaInternaRepository;

    @Autowired
    private EstadoClienteRepository estadoClienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private EstadoCuentaRepository estadoCuentaRepository;

    @GetMapping("/")
    public String doMostrarOpciones() {
        return "gestor/index";
    }

    @GetMapping("/pendientes")
    public String doMostrarPendientes(Model model) {
        List<ClienteEntity> list = this.clienteRepository.clientesNoAutorizados();
        separarIndividualesEmpresas(model, list);
        return "gestor/pendientes";
    }

    @GetMapping("/autorizar")
    public String doAutorizarCliente(@RequestParam("id") Integer idCliente, Model model) {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            return "redirect:/gestor/";
        }
        CuentaInternaEntity cuentaInterna = new CuentaInternaEntity();
        CuentaBancariaEntity cuentaBancaria = new CuentaBancariaEntity();

        cuentaInterna.setCuentaBancariaByCuentaBancaria(cuentaBancaria);
        cuentaInterna.setAutorizacionsById(null);

        cuentaInterna.setClienteByPropietario(cliente);

        model.addAttribute("cuenta", cuentaInterna);

        return "gestor/autorizar";
    }

    @PostMapping("/autorizar")
    public String doAutorizar(@ModelAttribute("autorizacionCliente") CuentaInternaEntity cuentaInterna) {
        EstadoCuentaEntity estadoCuenta = this.estadoCuentaRepository.findByEstado("ACTIVA");
        cuentaInterna.setEstadoCuentaByEstadoCuenta(estadoCuenta);

        cuentaInterna.setBloqueada((byte) 0);
        cuentaInterna.setCantidad(0.0);

        EmpleadoEntity gestor = this.empleadoRepository.findById(1).orElse(null); //TODO: do it using sessions
        cuentaInterna.getClienteByPropietario().setEmpleadoByAutorizador(gestor);

        this.clienteRepository.save(cuentaInterna.getClienteByPropietario());
        this.cuentaBancariaRepository.save(cuentaInterna.getCuentaBancariaByCuentaBancaria());
        this.cuentaInternaRepository.save(cuentaInterna);

        return "redirect:/gestor/pendientes";
    }

    @GetMapping("/rechazar")
    public String doRechazar(@RequestParam("id") Integer idCliente) {
        clienteRepository.deleteById(idCliente);
        return "redirect:/gestor/pendientes";
    }

    @GetMapping("/clientes")
    public String doListarClientes(Model model) {
        List<ClienteEntity> list = this.clienteRepository.clientesAutorizados();
        separarIndividualesEmpresas(model, list);
        model.addAttribute("filtro", new FiltroClientes());
        model.addAttribute("estados", this.estadoClienteRepository.findAll());
        return "gestor/clientes";
    }

    private void separarIndividualesEmpresas(Model model, List<ClienteEntity> list) {
        List<ClienteEntity> listPersonas = new ArrayList<>();
        List<ClienteEntity> listEmpresas = new ArrayList<>();
        for (ClienteEntity cliente : list) {
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
            separarIndividualesEmpresas(model, this.clienteRepository.clientesAutorizados());
            filtro = new FiltroClientes();
        } else if (filtro.getEstadoCliente().isEmpty()) {
            List<ClienteEntity> listPersonas = this.clienteRepository.buscarIndividualesPorNombre(filtro.getTexto());
            List<ClienteEntity> listEmpresas = this.clienteRepository.buscarEmpresaPorNombre(filtro.getTexto());

            model.addAttribute("personas", listPersonas);
            model.addAttribute("empresas", listEmpresas);
        } else if (filtro.getTexto().isEmpty()) {
            separarIndividualesEmpresas(model, this.clienteRepository.buscarPorEstado(filtro.getEstadoCliente()));
        } else {
            List<ClienteEntity> listPersonas = this.clienteRepository.buscarIndividualesPorNombreYEstado(filtro.getTexto(), filtro.getEstadoCliente());
            List<ClienteEntity> listEmpresas = this.clienteRepository.buscarEmpresaPorNombreYEstado(filtro.getTexto(), filtro.getEstadoCliente());

            model.addAttribute("personas", listPersonas);
            model.addAttribute("empresas", listEmpresas);
        }
        model.addAttribute("filtro", filtro);
        model.addAttribute("estados", this.estadoClienteRepository.findAll());
        return "gestor/clientes";
    }

    @GetMapping("/individual")
    public String doMostrarDetallesIndividual(@RequestParam("id") Integer idCliente, Model model) {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            return "gestor/index";
        }
        model.addAttribute("cliente", cliente);
        return "gestor/individual";
    }

    @GetMapping("/empresa")
    public String doMostrarDetallesEmpresa(@RequestParam("id") Integer idCliente, Model model) {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            return "gestor/index";
        }
        model.addAttribute("cliente", cliente);
        return "gestor/empresa";
    }

    @GetMapping("/operaciones")
    public String doMostrarOperacionesCuenta(@RequestParam("id") Integer idCuenta, Model model) {
        return getTransaccionesYOrdenar(model, idCuenta, "instruccion");
    }

    @PostMapping("/operaciones")
    public String doFiltraroperaciones(@ModelAttribute("filtro") FiltroOperaciones filtro, Model model) {
        if (filtro.getIban().isEmpty() && filtro.getFechaInstruccion().isEmpty() && filtro.getFechaEjecucion().isEmpty()) {
            return getTransaccionesYOrdenar(model, filtro.getIdCuenta(), filtro.getOrden());
        } else {
            CuentaInternaEntity cuenta = this.cuentaInternaRepository.findById(filtro.getIdCuenta()).orElse(null);
            if (cuenta == null) {
                return "gestor/index";
            }
            CuentaBancariaEntity cuentaBancaria = cuenta.getCuentaBancariaByCuentaBancaria();
            List<TransaccionEntity> transacciones = new ArrayList<>();
            transacciones.addAll(cuentaBancaria.getTransaccionsById_Entrantes());
            transacciones.addAll(cuentaBancaria.getTransaccionsById_Salientes());

            if(!filtro.getIban().isEmpty()){
                transacciones = transacciones.stream().filter(obj -> obj.getCuentaBancariaByCuentaDestino().getIban().contains(filtro.getIban()) || obj.getCuentaBancariaByCuentaOrigen().getIban().contains(filtro.getIban())).collect(Collectors.toList());
            }
            if(!filtro.getFechaInstruccion().isEmpty()){
                LocalDate fecha = LocalDate.parse(filtro.getFechaInstruccion());
                transacciones = transacciones.stream().filter(obj -> obj.getFechaInstruccion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
            }
            if(!filtro.getFechaEjecucion().isEmpty()){
                LocalDate fecha = LocalDate.parse(filtro.getFechaEjecucion());
                transacciones = transacciones.stream().filter(obj -> obj.getFechaEjecucion().toLocalDateTime().toLocalDate().equals(fecha)).collect(Collectors.toList());
            }

            ordenarTransacciones(transacciones, filtro.getOrden());

            model.addAttribute("cuenta", cuentaBancaria);
            model.addAttribute("lista", transacciones);
            model.addAttribute("filtro", filtro);

        }
        model.addAttribute("filtro", filtro);
        model.addAttribute("estados", this.estadoClienteRepository.findAll());
        return "gestor/operaciones";
    }

    private String getTransaccionesYOrdenar(Model model, int idCuenta, String orden) {
        CuentaInternaEntity cuenta = this.cuentaInternaRepository.findById(idCuenta).orElse(null);
        if (cuenta == null) {
            return "gestor/index";
        }
        CuentaBancariaEntity cuentaBancaria = cuenta.getCuentaBancariaByCuentaBancaria();
        List<TransaccionEntity> transaccionEntityList = new ArrayList<>();
        transaccionEntityList.addAll(cuentaBancaria.getTransaccionsById_Entrantes());
        transaccionEntityList.addAll(cuentaBancaria.getTransaccionsById_Salientes());
        ordenarTransacciones(transaccionEntityList, orden);
        model.addAttribute("cuenta", cuentaBancaria);
        model.addAttribute("lista", transaccionEntityList);
        model.addAttribute("filtro", new FiltroOperaciones(cuenta.getId()));
        return "gestor/operaciones";
    }

    private void ordenarTransacciones(List<TransaccionEntity> transacciones, String orden) {
        if (orden.equals("instruccion")) {
            transacciones.sort(new Comparator<TransaccionEntity>() {
                @Override
                public int compare(TransaccionEntity o1, TransaccionEntity o2) {
                    return o1.getFechaInstruccion().compareTo(o2.getFechaInstruccion());
                }
            });
        } else {
            transacciones.sort(new Comparator<TransaccionEntity>() {
                @Override
                public int compare(TransaccionEntity o1, TransaccionEntity o2) {
                    return o1.getFechaEjecucion().compareTo(o2.getFechaEjecucion());
                }
            });
        }
    }
}
