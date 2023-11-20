package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Variables;
import com.paqueteria.paqueteria_backend.repositorio.VariablesMejoraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariablesMejoraServicio {
    @Autowired
    private VariablesMejoraRepo variablesRepo;

    public Variables save(Variables entrada){
        return this.variablesRepo.save(entrada);
    }

    public Variables getVariables(int idVariables){return this.variablesRepo.getVariablesById(idVariables);}

}
