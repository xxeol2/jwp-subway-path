package subway.application.port.out.route;

import java.util.List;
import subway.domain.line.Line;
import subway.domain.station.Station;
import subway.domain.route.Route;

public interface RouteFinderPort {

    Route findRoute(Station source, Station target, List<Line> lines);
}
