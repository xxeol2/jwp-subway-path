package subway.domain;

import subway.exception.ApiIllegalArgumentException;

public class Line {

    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_COLOR_LENGTH = 20;

    private final Long id;
    private final String name;
    private final String color;

    public Line(final String name, final String color) {
        this(null, name, color);
    }

    public Line(final Long id, final String name, final String color) {
        validateName(name);
        validateColor(color);
        this.id = id;
        this.name = name.strip();
        this.color = color.strip();
    }

    private void validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new ApiIllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
        if (name.strip().length() > MAX_NAME_LENGTH) {
            throw new ApiIllegalArgumentException("이름은 " + MAX_NAME_LENGTH + "자 이하여야합니다.");
        }
    }

    private void validateColor(final String color) {
        if (color == null || color.isBlank()) {
            throw new ApiIllegalArgumentException("색상은 비어있을 수 없습니다.");
        }
        if (color.strip().length() > MAX_COLOR_LENGTH) {
            throw new ApiIllegalArgumentException("색상은 " + MAX_COLOR_LENGTH + "자 이하여야합니다.");
        }
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
