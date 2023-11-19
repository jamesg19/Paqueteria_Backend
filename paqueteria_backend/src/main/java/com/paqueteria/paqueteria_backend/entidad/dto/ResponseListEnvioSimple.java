package com.paqueteria.paqueteria_backend.entidad.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ResponseListEnvioSimple {
    private List<EnvioSimple> disponibles;
    private List<EnvioSimple> standBy;

}
