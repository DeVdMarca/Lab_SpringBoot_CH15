package co.cuerpoactivo.cuerpo_activo_api.service;

import co.cuerpoactivo.cuerpo_activo_api.dto.PlanRequest;
import co.cuerpoactivo.cuerpo_activo_api.exception.RecursoNoEncontradoException;
import co.cuerpoactivo.cuerpo_activo_api.model.Plan;
import co.cuerpoactivo.cuerpo_activo_api.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository repository;

    public PlanService(PlanRepository repository) {
        this.repository = repository;
    }

    public List<Plan> listarTodos() {
        return repository.findAll();
    }

    public Plan buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el plan con id " + id));
    }

    public Plan crear(PlanRequest request) {
        return repository.save(request.aEntidad());
    }

    public Plan actualizar(Long id, PlanRequest request) {
        Plan plan = buscarPorId(id);
        plan.setNombre(request.nombre());
        plan.setDuracionMeses(request.duracionMeses());
        plan.setPrecio(request.precio());
        return repository.save(plan);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe el plan con id " + id);
        }
        repository.deleteById(id);
    }
}

