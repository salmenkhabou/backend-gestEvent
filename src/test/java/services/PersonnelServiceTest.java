package services;

import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.repository.PersonnelRepository;
import com.exemple.backendgestevent.services.PersonnelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.exemple.backendgestevent.entity.Role.technician;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonnelServiceTest {

    @Mock
    private PersonnelRepository personnelRepository;

    @InjectMocks
    private PersonnelService personnelService;

    private Personnel personnel;

    @BeforeEach
    void setUp() {
        // Initialisation d'un personnel pour les tests
        personnel = new Personnel(UUID.randomUUID(), "John Doe", "password", "john.doe@example.com", "123456789", technician, true);
    }

    @Test
    void testGetAvailablePersonnels() {
        // Simuler la liste des personnels disponibles
        when(personnelRepository.findByDisponibleTrue()).thenReturn(Arrays.asList(personnel));

        // Appeler la méthode
        List<Personnel> availablePersonnels = personnelService.getAvailablePersonnels();

        // Vérifier les résultats
        assertNotNull(availablePersonnels);
        assertFalse(availablePersonnels.isEmpty());
        assertEquals(1, availablePersonnels.size());

        // Vérification des appels
        verify(personnelRepository, times(1)).findByDisponibleTrue();
    }

    @Test
    void testGetAvailablePersonnelsEmpty() {
        // Simuler aucune personne disponible
        when(personnelRepository.findByDisponibleTrue()).thenReturn(Arrays.asList());

        // Appeler la méthode
        List<Personnel> availablePersonnels = personnelService.getAvailablePersonnels();

        // Vérifier les résultats
        assertNotNull(availablePersonnels);
        assertTrue(availablePersonnels.isEmpty());

        // Vérification des appels
        verify(personnelRepository, times(1)).findByDisponibleTrue();
    }

    @Test
    void testGetPersonnelById() {
        // Simuler la recherche d'un personnel par ID
        when(personnelRepository.findById(any(UUID.class))).thenReturn(Optional.of(personnel));

        // Appeler la méthode
        Optional<Personnel> foundPersonnel = personnelService.getPersonnelById(personnel.getId());

        // Vérifier les résultats
        assertTrue(foundPersonnel.isPresent());
        assertEquals(personnel.getId(), foundPersonnel.get().getId());

        // Vérification des appels
        verify(personnelRepository, times(1)).findById(personnel.getId());
    }

    @Test
    void testGetPersonnelByIdNotFound() {
        // Simuler l'absence de personnel avec cet ID
        when(personnelRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Appeler la méthode
        Optional<Personnel> foundPersonnel = personnelService.getPersonnelById(UUID.randomUUID());

        // Vérifier que le personnel n'a pas été trouvé
        assertFalse(foundPersonnel.isPresent());

        // Vérification des appels
        verify(personnelRepository, times(1)).findById(any(UUID.class));
    }
}
