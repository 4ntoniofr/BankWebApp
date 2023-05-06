/**
 * @author Jose Bravo Marques
 */

package es.taw.grupo25.controller;

import es.taw.grupo25.dto.*;
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
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cajero")
public class cajeroController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    DireccionService direccionService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private CuentaInternaService cuentaInternaService;

    @Autowired
    PagoService pagoService;

    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @Autowired
    private MonedaService monedaService;

    @Autowired
    TransaccionService transaccionService;

    @Autowired
    EstadoCuentaService estadoCuentaService;

    @Autowired
    CambioDivisaService cambioDivisaService;

    @GetMapping("")
    public String cajero(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlto = "cajero/cajero";
        if (usuario == null || !usuario.soyCliente()) {
            urlto = "redirect:/login";
        }
        return urlto;
    }

    @GetMapping("/datos")
    public String datos(Model model, HttpSession session) {
        String urlto = "cajero/datos";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            urlto = "redirect:/login";
        } else {
            if (usuario.getClientesById().getAutorizador()) {
                Cliente cliente = clienteService.findById(usuario.getClientesById().getId());
                model.addAttribute("cliente", cliente);
            } else {
                urlto = "redirect:/cajero";
            }
        }
        return urlto;
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        direccionService.saveDireccion(cliente.getDireccionByDireccion());
        personaService.guardarPersona(cliente.getPersonaByPersonaId());
        return "redirect:/cajero";
    }

    @GetMapping("/operacionesLista")
    public String listarOperaciones(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta) {
        String urlto = "redirect:/cajero/operacionesLista";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            urlto = "redirect:/login";
        } else {
            if (usuario.getClientesById().getAutorizador()) {
                CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
                if (cuenta != null) {
                    if (cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId()) {
                        FiltroOperaciones filtro = new FiltroOperaciones(cuenta.getCuentaBancariaByCuentaBancaria().getId());
                        List<Pago> pagos = pagoService.findByCuentaId(cuenta.getCuentaBancariaByCuentaBancaria().getId());
                        model.addAttribute("filtro", filtro);
                        model.addAttribute("pagos", pagos);
                        model.addAttribute("iban", cuenta.getCuentaBancariaByCuentaBancaria().getIban());
                        urlto = "cajero/operacionesLista";
                    }
                }
            } else {
                urlto = "redirect:/cajero";
            }
        }
        return urlto;
    }

    @PostMapping("/operacionesLista")
    public String aplicarFiltroOperaciones(@ModelAttribute("filtro") FiltroOperaciones filtro, Model model, HttpSession session) {
        String urlTo = "/login";
        List<Pago> pagos = pagoService.findByCuentaId(filtro.getIdCuenta());

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            urlTo = "redirect:/login";
        } else {
            urlTo = "cajero/operacionesLista";
            if (filtro == null || filtro.getFechaEjecucion().isEmpty() && filtro.getFechaInstruccion().isEmpty() && filtro.getIban().isEmpty()) {

            } else {
                if (!filtro.getIban().isEmpty()) {
                    pagos = pagos.stream().filter(obj -> obj.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban().contains(filtro.getIban())).collect(Collectors.toList());
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
            CuentaBancaria cuenta = cuentaBancariaService.findById(filtro.getIdCuenta());
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

    private Double getCuentaCantidad(Double cantidad, Moneda moneda_anterior, Moneda moneda_nueva) {
        double cantidadEnEuros = cantidad * moneda_anterior.getCambioEuro();
        return cantidadEnEuros / moneda_nueva.getCambioEuro();
    }

    @GetMapping("/transferencias")
    public String hacerTransferencia(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cajero/transferencias";
        if (usuario == null) {
            urlTo = "redirect:/login";
        } else {
            if (usuario.getClientesById().getAutorizador()) {
                CuentaBancaria cuenta = cuentaBancariaService.findById(idCuenta);
                if (cuenta != null) {
                    if (cuenta.getCuentaInternasById().getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId()) {
                        if (cuenta.getCuentaInternasById().getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")) {
                            model.addAttribute("pago", new Pago());
                            model.addAttribute("monedas", monedaService.findAll());
                            urlTo = "cajero/transferencias";
                        } else {
                            model.addAttribute("error", "No se pueden hacer transferencias en cuentas que no esten Activas.");
                            urlTo = "/cajero/transferencias";
                        }
                    }
                }
            } else {
                urlTo = "redirect:/cajero";
            }
        }
        return urlTo;
    }

    @PostMapping("/transferencias")
    public String guardarTransferencia(@ModelAttribute("pago") Pago pago, @RequestParam("idCuenta") int idCuenta, Model model, HttpSession session) {
        CuentaBancaria cuenta = cuentaBancariaService.findById(idCuenta);
        CuentaBancaria cuenta_destino = cuentaBancariaService.findByIban(pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban());
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cajero/transferencias";
        if (usuario == null) {
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
            }
        }
        return urlTo;
    }

    /*
        Helper method for post transferencia
     */
    private boolean isCorrect(CuentaBancaria cuenta, CuentaBancaria cuenta_destino, Pago pago, Model model, HttpSession session, Integer idCuenta) {
        if (cuenta_destino != null) {
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
        } else {
            model.addAttribute("errorTransferencia", "ERROR: El iban introducido no existe.");
            return false;
        }
    }

    @GetMapping("/desbloquear")
    public String intentarDesbloqueo(@RequestParam("idCuenta") int idCuenta, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cajero/desbloquear";
        if (usuario == null) {
            urlTo = "redirect:/login";
        } else {
            if (usuario.getClientesById().getAutorizador()) {
                CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
                if (cuenta != null) {
                    if (cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId()) {
                        model.addAttribute("cuenta", cuenta);
                        urlTo = "cajero/desbloquear";
                    }
                }
            } else {
                urlTo = "redirect:/cajero";
            }
        }
        return urlTo;
    }

    @PostMapping("/desbloquear")
    public String solicitarDesbloqueo(@ModelAttribute("cuenta") CuentaInterna cuenta, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlTo = "redirect:/cajero";
        if (usuario == null) {
            urlTo = "redirect:/login";
        } else {
            CuentaInterna cuenta_cambio = cuentaInternaService.findById(cuenta.getId());
            if (cuenta_cambio != null) {
                if (cuenta_cambio.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId()) {
                    EstadoCuenta estado = estadoCuentaService.findByEstado("SOLICITADO");
                    cuenta_cambio.setEstadoCuentaByEstadoCuenta(estado);
                    cuentaInternaService.guardarCuenta(cuenta_cambio);
                }
            }
        }
        return urlTo;
    }

    @GetMapping("/cambio")
    public String cambiarDivisas(Model model, HttpSession session, @RequestParam("idCuenta") int idCuenta) {
        String urlTo = "redirect:/cajero/cambio";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            urlTo = "redirect:/login";
        } else {
            if (usuario.getClientesById().getAutorizador()) {
                CuentaInterna cuenta = cuentaInternaService.findById(idCuenta);
                if (cuenta != null) {
                    if (cuenta.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId()) {
                        if (cuenta.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")) {
                            model.addAttribute("cuenta", cuenta);
                            model.addAttribute("monedas", monedaService.findAll());
                            model.addAttribute("cambioDivisas", cambioDivisaService.findByCuentaId(cuenta.getCuentaBancariaByCuentaBancaria().getId()));
                            urlTo = "cajero/cambio";
                        } else {
                            model.addAttribute("error", "No se pueden hacer cambios de divisa en cuentas que no esten Activas.");
                            urlTo = "/cajero/cambio";
                        }
                    }
                }
            } else {
                urlTo = "redirect:/cajero";
            }
        }
        return urlTo;
    }

    @PostMapping("/cambio")
    public String guardarCambioDivisas(@ModelAttribute("cuenta") CuentaInterna cuenta, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        } else {
            CuentaInterna cuenta_cambio = cuentaInternaService.findById(cuenta.getId());
            if (cuenta_cambio != null) {
                if (cuenta_cambio.getClienteByPropietario().getUsuarioByUsuarioId().getId() == usuario.getId() && cuenta_cambio.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")) {
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
        return "redirect:/cajero/cambio";
    }


}
