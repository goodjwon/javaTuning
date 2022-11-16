package com.ezezbiz.demo.enums;

import ch.qos.logback.core.util.TimeUtil;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class EnumsMapTest {

    @Test
    public void whenContructedWithEnumType_ThenOnlyAcceptThatAsKey(){
        Map dayMap = new EnumMap<>(DayOfWeek.class);
        assertThatCode(
                ()->dayMap.put(TimeUnit.NANOSECONDS, "NANOSECONDS"))
                .isInstanceOf(ClassCastException.class);
    }

    @Test
    public void  whenConstructedWithEnumMap_ThenSameKeyTypeAndInitialMappings(){
        EnumMap<DayOfWeek, String> activityMap = new EnumMap<>(DayOfWeek.class);
        activityMap.put(DayOfWeek.MONDAY, "Soccer");
        activityMap.put(DayOfWeek.TUESDAY, "Basketball");

        EnumMap<DayOfWeek, String> copyMap = new EnumMap<DayOfWeek, String>(activityMap);
        assertThat(copyMap.size()).isEqualTo(2);
        assertThat(copyMap.get(DayOfWeek.MONDAY)).isEqualTo("Soccer");
        assertThat(copyMap.get(DayOfWeek.TUESDAY)).isEqualTo("Basketball");

    }
}
