import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RouteCalculatorTest extends TestCase {
    List<Station> rout = new ArrayList<>();
    List<Station> routForestAndGates = new ArrayList<>();

    StationIndex stationIndex = new StationIndex();

    RouteCalculator calculator = new RouteCalculator(stationIndex);
    @Override
    protected void setUp() throws Exception {


        Line line1 = new Line(1, "Красная");
        Line line2 = new Line(2,"Синяя");
        Line line3 = new Line(3, "Оранжевая");

        Station station1 = new Station("Лесная", line1);
        Station station2 = new Station("Выборгская", line1);
        Station station3 = new Station("Площадь ленина", line1);
        Station station4 = new Station("Чернышевская", line1);
        Station station5 = new Station("Площадь восстания", line1);
        Station station6 = new Station("Владимирская", line1);
        Station station7 = new Station("Пушкинская", line1);
        Station station8 = new Station("Технологический институт", line1);
        Station station9 = new Station("Технологический институт", line2);
        Station station10 = new Station("Фрунзенская", line2);
        Station station11 = new Station("Московские ворота", line2);
        Station station12 = new Station("Достоевская", line3);
        Station station13 = new Station("Лиговский проспект", line3);
        Station station14 = new Station("Площадь Александра Невского", line3);
        Station station15 = new Station("Новочеркасская", line3);

        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line1.addStation(station4);
        line1.addStation(station5);
        line1.addStation(station6);
        line1.addStation(station7);
        line1.addStation(station8);
        line2.addStation(station9);
        line2.addStation(station10);
        line2.addStation(station11);
        line3.addStation(station12);
        line3.addStation(station13);
        line3.addStation(station14);
        line3.addStation(station15);

        List<Station> connectOne = new ArrayList<>();
        connectOne.add(station8);
        connectOne.add(station9);

        List<Station> connectTwo = new ArrayList<>();
        connectTwo.add(station11);
        connectTwo.add(station12);



        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);
        stationIndex.addStation(station7);
        stationIndex.addStation(station8);
        stationIndex.addStation(station9);
        stationIndex.addStation(station10);
        stationIndex.addStation(station11);
        stationIndex.addStation(station12);
        stationIndex.addStation(station13);
        stationIndex.addStation(station14);
        stationIndex.addStation(station15);
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addConnection(connectOne);
        stationIndex.addConnection(connectTwo);

        routForestAndGates.add(station1);
        routForestAndGates.add(station2);
        routForestAndGates.add(station3);
        routForestAndGates.add(station4);
        routForestAndGates.add(station5);
        routForestAndGates.add(station6);
        routForestAndGates.add(station7);
        routForestAndGates.add(station8);
        routForestAndGates.add(station9);
        routForestAndGates.add(station10);
        routForestAndGates.add(station11);


        rout.add(station1);
        rout.add(station2);
        rout.add(station3);
        rout.add(station4);
        rout.add(station5);
        rout.add(station6);
        rout.add(station7);
        rout.add(station8);
        rout.add(station9);
        rout.add(station10);
        rout.add(station11);
        rout.add(station12);
        rout.add(station13);
        rout.add(station14);
        rout.add(station15);
    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(routForestAndGates);
        double expected = 26;
        assertEquals(expected, actual);
    }


    public void testGetRouteOnTheLine(){
        Station from = stationIndex.getStation("Лесная",1);
        Station to = stationIndex.getStation("Пушкинская", 1);
        List<Station> actual = calculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(rout.get(0),rout.get(1),rout.get(2),rout.get(3),rout.get(4),rout.get(5),
                rout.get(6));
        assertEquals(expected,actual);
    }

    public void testGetRouteWithOneConnection(){
        Station from = stationIndex.getStation("Лесная",1);
        Station to = stationIndex.getStation("Московские ворота", 2);
        List<Station> actual = calculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(rout.get(0),rout.get(1),rout.get(2),rout.get(3),rout.get(4),rout.get(5),
                rout.get(6),rout.get(7),rout.get(8),rout.get(9),rout.get(10));
        assertEquals(expected,actual);
    }

    public void testgetRouteViaConnectedLine(){
        Station from = stationIndex.getStation("Лесная",1);
        Station to = stationIndex.getStation("Московские ворота", 2);
        List<Station> actual = calculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(rout.get(0),rout.get(1),rout.get(2),rout.get(3),rout.get(4),rout.get(5),
                rout.get(6),rout.get(7),rout.get(8),rout.get(9),rout.get(10));
        assertEquals(expected,actual);
    }

    public void testGetRouteWithTwoConnections(){
        Station from = stationIndex.getStation("Лесная",1);
        Station to = stationIndex.getStation("Новочеркасская", 3);
        List<Station> actual = calculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(rout.get(0),rout.get(1),rout.get(2),rout.get(3),rout.get(4),rout.get(5),
                rout.get(6),rout.get(7),rout.get(8),rout.get(9),rout.get(10),rout.get(11),rout.get(12),rout.get(13),
                rout.get(14));
        assertEquals(expected,actual);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
