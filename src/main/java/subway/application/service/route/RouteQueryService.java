package subway.application.service.route;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subway.application.port.in.route.FindRouteUseCase;
import subway.application.port.in.route.dto.command.FindRouteCommand;
import subway.application.port.in.route.dto.response.RouteQueryResponse;
import subway.application.port.out.line.LoadLinePort;
import subway.application.port.out.route.RouteFinderPort;
import subway.application.port.out.station.LoadStationPort;
import subway.common.exception.NoSuchStationException;
import subway.common.mapper.RouteMapper;
import subway.domain.Line;
import subway.domain.Route;
import subway.domain.Station;

@Service
@Transactional(readOnly = true)
public class RouteQueryService implements FindRouteUseCase {

    private final LoadLinePort loadLinePort;
    private final LoadStationPort loadStationPort;
    private final RouteFinderPort routeFinderPort;

    public RouteQueryService(final LoadLinePort loadLinePort,
            final LoadStationPort loadStationPort,
            final RouteFinderPort routeFinderPort) {
        this.loadLinePort = loadLinePort;
        this.loadStationPort = loadStationPort;
        this.routeFinderPort = routeFinderPort;
    }

    @Override
    public RouteQueryResponse foundRoute(final FindRouteCommand command) {
        List<Line> lines = loadLinePort.findAll();
        Optional<Station> source = loadStationPort.findById(command.getSourceStationId());
        Optional<Station> target = loadStationPort.findById(command.getTargetStationId());
        if (source.isEmpty() || target.isEmpty()) {
            throw new NoSuchStationException();
        }

        Route route = routeFinderPort.findRoute(source.get(), target.get(), lines);

        return RouteMapper.toResponse(route);
    }
}
