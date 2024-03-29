package inflearnproject.anoncom.notification.service;

import inflearnproject.anoncom.domain.Notification;
import inflearnproject.anoncom.notification.dto.NotificationAddDto;
import inflearnproject.anoncom.notification.dto.NotificationSearchCondition;
import inflearnproject.anoncom.notification.exception.NotExistNotificationException;
import inflearnproject.anoncom.notification.repository.NotificationDSLRepository;
import inflearnproject.anoncom.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static inflearnproject.anoncom.error.ExceptionMessage.NO_SUCH_NOTIFICATION;

@Transactional
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationDSLRepository notificationDSLRepository;

    public void addNotification(NotificationAddDto notificationAddDto) {
        Notification notification = Notification.builder()
                .title(notificationAddDto.getTitle())
                .category(notificationAddDto.getCategory())
                .content(notificationAddDto.getContent())
                .location(notificationAddDto.getLocation())
                .build();
        notificationRepository.save(notification);
    }

    public List<Notification> getNotifications(NotificationSearchCondition cond) {
        return notificationDSLRepository.findNotificationsByCondition(cond);
    }

    public Notification getOneNotification(Long id) {
        return notificationRepository.findById(id).orElseThrow(
                () -> new NotExistNotificationException(NO_SUCH_NOTIFICATION)
        );
    }

    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new NotExistNotificationException(NO_SUCH_NOTIFICATION));
        notificationRepository.delete(notification);
    }
}
