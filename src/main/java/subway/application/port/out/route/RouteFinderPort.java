package subway.application.port.out.route;

import java.util.List;
import subway.domain.Line;
import subway.domain.Route;
import subway.domain.Station;

public interface RouteFinderPort {

    Route findRoute(Station source, Station target, List<Line> lines);
}
