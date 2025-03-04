package subway.application.service.line;

import java.util.List;
import java.util.stream.Collectors;
import subway.adapter.out.persistence.entity.LineEntity;
import subway.application.port.in.line.dto.response.LineQueryResponse;
import subway.application.port.in.station.dto.response.StationQueryResponse;
import subway.application.service.station.StationMapper;
import subway.domain.line.Line;
import subway.domain.line.LineInfo;

public class LineMapper {

    private LineMapper() {
    }

    public static LineQueryResponse toResponse(Line line) {
        List<StationQueryResponse> stations = line.findOrderedStation().stream()
                .map(StationMapper::toResponse)
                .collect(Collectors.toList());
        return new LineQueryResponse(line.getId(), line.getName(), line.getColor(), stations);
    }

    public static LineEntity toEntity(final LineInfo lineInfo) {
        return new LineEntity(lineInfo.getName(), lineInfo.getColor(), lineInfo.getSurcharge().getValue());
    }

    public static LineEntity toEntity(final long lineId, final LineInfo lineInfo) {
        return new LineEntity(lineId, lineInfo.getName(), lineInfo.getColor(), lineInfo.getSurcharge().getValue());
    }
}
