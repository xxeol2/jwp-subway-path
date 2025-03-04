package subway.adapter.in.web.section;

import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import subway.application.port.in.section.RemoveStationFromLineUseCase;
import subway.application.port.in.section.dto.command.RemoveStationFromLineCommand;

@RestController
public class RemoveStationFromLineController {

    private final RemoveStationFromLineUseCase removeStationFromLineUseCase;

    public RemoveStationFromLineController(
            final RemoveStationFromLineUseCase removeStationFromLineUseCase) {
        this.removeStationFromLineUseCase = removeStationFromLineUseCase;
    }

    @DeleteMapping("/lines/{lineId}/stations/{stationId}")
    public ResponseEntity<Void> deleteSection(@PathVariable @NotNull(message = "노선 id가 없습니다.") Long lineId,
            @PathVariable @NotNull(message = "역 id가 없습니다.") Long stationId) {
        removeStationFromLineUseCase.removeStation(new RemoveStationFromLineCommand(lineId, stationId));
        return ResponseEntity.noContent().build();
    }
}
