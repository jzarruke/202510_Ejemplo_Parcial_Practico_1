package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsultaMedicaService {
    @Autowired
    private ConsultaMedicaRepository consultaMedicaRepository;

    @Transactional
    public ConsultaMedicaEntity createConsultaMedica(ConsultaMedicaEntity consultaMedica) throws IllegalOperationException {
        log.info("Creando una nueva consulta medica");
        Date fechaCons = consultaMedica.getFecha();
        if (!fechaCons.after(Calendar.getInstance().getTime())) {
            throw new IllegalOperationException("La fecha de la consulta no puede ser antes de la fecha actual");
        }
        return consultaMedicaRepository.save(consultaMedica);
    }
}