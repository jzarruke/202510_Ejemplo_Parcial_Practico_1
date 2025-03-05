package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public PacienteEntity createPaciente(PacienteEntity paciente) throws IllegalOperationException {
        log.info("Creando un nuevo paciente");
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new IllegalOperationException("El nombre del paciente es invalido");
        }
        if (paciente.getEdad() == null || paciente.getEdad() <= 0) {
            throw new IllegalOperationException("La edad del paciente es invalida");
        }
        if (paciente.getCorreo() == null || paciente.getCorreo().isEmpty()) {
            throw new IllegalOperationException("El correo del paciente es invalido");
        }
        if (paciente.getCelular() == null || paciente.getCelular() <= 0) {
            throw new IllegalOperationException("El celular del paciente es invalido");
        }
        return pacienteRepository.save(paciente);
    }
}