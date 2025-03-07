package subway.fixture;

import java.util.ArrayList;
import subway.adapter.in.web.line.dto.CreateLineRequest;
import subway.adapter.out.persistence.entity.LineEntity;
import subway.domain.line.Line;

@SuppressWarnings("NonAsciiCharacters")
public class LineFixture {

    public static class 이호선 {

        private static final Long ID = 1L;
        private static final String NAME = "2호선";
        private static final String COLOR = "GREEN";
        private static final int SURCHARGE = 500;

        public static final Line LINE = new Line(ID, NAME, COLOR, SURCHARGE, new ArrayList<>());
        public static final LineEntity ENTITY = new LineEntity(ID, NAME, COLOR, SURCHARGE);
        public static final CreateLineRequest REQUEST = new CreateLineRequest(NAME, COLOR, SURCHARGE);
    }

    public static class 삼호선 {

        private static final Long ID = 2L;
        private static final String NAME = "3호선";
        private static final String COLOR = "ORANGE";
        private static final int SURCHARGE = 0;

        public static final Line LINE = new Line(ID, NAME, COLOR, SURCHARGE, new ArrayList<>());
        public static final LineEntity ENTITY = new LineEntity(ID, NAME, COLOR, SURCHARGE);
        public static final CreateLineRequest REQUEST = new CreateLineRequest(NAME, COLOR, SURCHARGE);
    }
}
