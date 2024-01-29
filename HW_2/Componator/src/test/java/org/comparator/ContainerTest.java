package org.comparator;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class ContainerTest{
    private Container container;
    @BeforeEach
    void setUp() {
        container = mock(Container.class);
    }

    @AfterEach
    void tearDown() {
        // Можно использовать этот блок для освобождения ресурсов, если необходимо
    }

    @Test
    void getWeight() {
        container.getWeight();

        verify(container, times(1)).getWeight();
    }


    @Test
    void print() {
        container.print();
        verify(container, times(1)).print();
    }
}