# Compose 2026 - Backend Integration: Notifications & Analytics
## 5.1 REAL-TIME UPDATES
- **FCM Badging:** `BadgedBox` ve reaktif `hasUnread` mühürleri ile bildirim merkezi yönetilmelidir.
- **Permission:** Android 13+ bildirim izinleri `rememberNotificationPermission` ile mühürlenmelidir.

## 5.2 BEHAVIORAL ANALYTICS
- **Scroll Tracking:** `snapshotFlow` ile kullanıcının hangi içeriğe ne kadar süre (View Time) baktığı fiziksel olarak tescil edilmelidir.
- **Lifecycle Events:** `LifecycleEventObserver` ile ekran görüntüleme ve funnel analizleri mühürlenmelidir.
