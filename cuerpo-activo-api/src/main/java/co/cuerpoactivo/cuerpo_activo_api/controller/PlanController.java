package co.cuerpoactivo.cuerpo_activo_api.controller;

import co.cuerpoactivo.cuerpo_activo_api.model.Plan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/planes")

public class PlanController {

    private List<Plan> planes = new ArrayList<>();
    private Long siguienteId = 4L;

    public PlanController() {
        planes.add(new Plan(1L, "Plan mensual", 1, 120000));
        planes.add(new Plan(2L, "Plan trimestral", 3, 330000));
        planes.add(new Plan(3L, "Plan anual", 12, 1200000));
    }
    @GetMapping
    public List<Plan> obtenerTodos() {
        return planes;
    }

    @GetMapping("/{id}")
    public Plan obtenerPorId(@PathVariable Long id) {
        for (Plan plan : planes) {
            if (plan.getId().equals(id)) {
                return plan;
            }
        }
        return null;
    }

    @PostMapping
    public Plan crearPlan(@RequestBody Plan nuevoPlan) {
        nuevoPlan.setId(siguienteId++);
        planes.add(nuevoPlan);
        return nuevoPlan;
    }

}
