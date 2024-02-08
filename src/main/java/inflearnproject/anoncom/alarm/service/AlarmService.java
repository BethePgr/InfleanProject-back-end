package inflearnproject.anoncom.alarm.service;

import inflearnproject.anoncom.alarm.error.NoAlarmException;
import inflearnproject.anoncom.alarm.repository.AlarmRepository;
import inflearnproject.anoncom.comment.exception.NotSameUserException;
import inflearnproject.anoncom.domain.Alarm;
import inflearnproject.anoncom.domain.UserEntity;
import inflearnproject.anoncom.error.ExceptionMessage;
import inflearnproject.anoncom.user.exception.NoUserEntityException;
import inflearnproject.anoncom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static inflearnproject.anoncom.error.ExceptionMessage.NO_ALARM;

@Transactional
@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    public void addAlarm(UserEntity to, String content) {
        Alarm alarm = Alarm.builder()
                .user(to)
                .message(content)
                .isRead(false)
                .build();

        alarmRepository.save(alarm);
    }

    public List<Alarm> getAlarms(Long userId) {
        return alarmRepository.findByUserId(userId);
    }

    public void deleteOneAlarm(Long memberId, Long alarmId) {
        UserEntity user = userRepository.findById(memberId).orElseThrow(
                () -> new NoUserEntityException(ExceptionMessage.NO_SAME_INFO_USER_MESSAGE)
        );
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(
                () -> new NoAlarmException(NO_ALARM)
        );
        if (alarm.getUser() != user) {
            throw new NotSameUserException(ExceptionMessage.NOT_SAME_USER);
        }
        alarmRepository.delete(alarm);
    }

    public void deleteAlarms(Long memberId) {
        UserEntity user = userRepository.findById(memberId).orElseThrow(
                () -> new NoUserEntityException(ExceptionMessage.NO_SAME_INFO_USER_MESSAGE)
        );
        alarmRepository.deleteAllByUserId(user.getId());
    }
}
