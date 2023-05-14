package subway.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import subway.adapter.out.persistence.dao.StationDao;
import subway.adapter.out.persistence.entity.StationEntity;
import subway.application.port.out.station.LoadStationPort;
import subway.application.port.out.station.PersistStationPort;
import subway.domain.Station;
import subway.common.mapper.StationMapper;

@Repository
public class StationJdbcAdapter implements LoadStationPort, PersistStationPort {

    private final StationDao stationDao;

    public StationJdbcAdapter(final StationDao stationDao) {
        this.stationDao = stationDao;
    }

    @Override
    public Optional<Station> findById(final Long id) {
        Optional<StationEntity> entity = stationDao.findById(id);
        if (entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(StationMapper.toStation(entity.get()));
    }

    @Override
    public List<Station> findAll() {
        List<StationEntity> entities = stationDao.findAll();
        return entities.stream()
                .map(StationMapper::toStation)
                .collect(Collectors.toList());
    }

    @Override
    public long create(final Station station) {
        StationEntity entity = stationDao.insert(StationMapper.toEntity(station));
        return entity.getId();
    }

    @Override
    public void update(final Station station) {
        stationDao.update(StationMapper.toEntity(station));
    }

    @Override
    public void deleteById(final long stationId) {
        stationDao.deleteById(stationId);
    }
}
