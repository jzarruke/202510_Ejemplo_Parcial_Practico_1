package co.edu.uniandes.dse.parcialprueba.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteConsultaMedicaService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaMedicaRepository consultaMedicaRepository;

    @Transactional
    public ConsultaMedicaEntity addConsulta (Long idPaciente, Long idConsulta) throws EntityNotFoundException, IllegalOperationException {
        PacienteEntity paciente = pacienteRepository.findById(idPaciente).orElseThrow(() -> new EntityNotFoundException("El paciente con id " + idPaciente + " no existe"));
        ConsultaMedicaEntity consultaEntity = consultaMedicaRepository.findById(idConsulta).orElseThrow(() -> new EntityNotFoundException("La consulta con id " + idConsulta + " no existe"));
        for (ConsultaMedicaEntity consulta: paciente.getConsultas()) {
            if (consulta.getFecha().equals(consultaEntity.getFecha())) {
                throw new IllegalOperationException("El paciente ya tiene una consulta programada para la fecha de la consulta");
            }
        }
        paciente.getConsultas().add(consultaEntity);
        return consultaEntity;
    }

    @Transactional
    public List<ConsultaMedicaEntity> getConsultasProgramadas (Long idPaciente) throws EntityNotFoundException {
        PacienteEntity paciente = pacienteRepository.findById(idPaciente).orElseThrow(() -> new EntityNotFoundException("El paciente con id " + idPaciente + " no existe"));
        List<ConsultaMedicaEntity> consultas = new ArrayList<>();
        for (ConsultaMedicaEntity consulta: paciente.getConsultas()) {
            if (consulta.getFecha().after(Calendar.getInstance().getTime())) {
                consultas.add(consulta);
            }
        }
        return consultas;
    }
}
