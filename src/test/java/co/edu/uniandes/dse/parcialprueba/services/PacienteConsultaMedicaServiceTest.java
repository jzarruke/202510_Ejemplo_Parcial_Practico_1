package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(PacienteConsultaMedicaService.class)
public class PacienteConsultaMedicaServiceTest {
    @Autowired
    private PacienteConsultaMedicaService pacienteConsultaMedicaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private PacienteEntity paciente = new PacienteEntity();
	private List<ConsultaMedicaEntity> consultaList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ConsultaMedicaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PacienteEntity paciente = factory.manufacturePojo(PacienteEntity.class);
            entityManager.persist(paciente);
        }
    }

    @Test
    public void testAddConsultaMedica() throws IllegalOperationException {
        
    }
}