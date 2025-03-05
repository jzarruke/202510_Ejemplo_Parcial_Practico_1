package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(ConsultaMedicaService.class)
public class ConsultaMedicaServiceTest {
    @Autowired
    private ConsultaMedicaService consultaMedicaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

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
            ConsultaMedicaEntity consultaMedica = factory.manufacturePojo(ConsultaMedicaEntity.class);
            entityManager.persist(consultaMedica);
        }
    }

    @Test
    public void testCreateConsultaMedica() throws IllegalOperationException {
        ConsultaMedicaEntity consultaMedica = factory.manufacturePojo(ConsultaMedicaEntity.class);
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); 
		calendar.add(Calendar.DATE, +5);
		consultaMedica.setFecha(calendar.getTime());
        ConsultaMedicaEntity result = consultaMedicaService.createConsultaMedica(consultaMedica);
        assertNotNull(result);

        ConsultaMedicaEntity consulta = entityManager.find(ConsultaMedicaEntity.class, result.getId());
        assertEquals(consultaMedica.getId(), consulta.getId());
		assertEquals(consultaMedica.getCausa(), consulta.getCausa());
        assertEquals(consultaMedica.getFecha(), consulta.getFecha());
    }
}