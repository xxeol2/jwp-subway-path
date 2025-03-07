package subway.application.port.out.line;

import subway.domain.line.Line;
import subway.domain.line.LineInfo;

public interface PersistLinePort {

    long create(LineInfo lineInfo);

    void updateInfo(long lineId, LineInfo lineInfo);

    void updateSections(Line line);

    void deleteSectionsByLineId(long lineId);

    void deleteById(long lineId);
}
