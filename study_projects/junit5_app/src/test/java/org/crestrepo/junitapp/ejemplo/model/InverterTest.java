package org.crestrepo.junitapp.ejemplo.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class InverterTest {

    @Test
    public void shouldInvert() {
        String inverted = Inverter.invert(null);

        assertAll();
    }
}
