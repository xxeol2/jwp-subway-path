package subway.domain.route;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import subway.exception.SubwayIllegalArgumentException;
import subway.domain.line.Line;
import subway.domain.section.Section;
import subway.fixture.LineFixture.이호선;
import subway.fixture.SectionFixture.삼호선_잠실_고터_2;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RouteSectionTest {

    @Test
    void 해당_노선의_구간이_아닌_경우_예외() {
        // given
        Line line = 이호선.LINE;
        Section section = 삼호선_잠실_고터_2.SECTION;

        // when then
        assertThatThrownBy(() -> new RouteSection(line, section))
                .isInstanceOf(SubwayIllegalArgumentException.class)
                .hasMessage("해당 노선의 구간이 아닙니다.");
    }
}
