# vietnamese-chronology
An implementation of the Vietnamese lunisolar calendar based on Meeus' astronomical algorithms.

## Astronomical calculations
- Lunar phases
- Solar positions

## Calendrical events
- Lunisolar date
- Solar terms
- Sexagenary cycle

## Extensions
- Holidays
- i18n support

## Usage

```java
LocalDate[] solarDates = {
    LocalDate.of(2025, 7, 24),
    LocalDate.of(2027, 2, 10),
    LocalDate.of(2028, 2, 26)
};

ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");
Locale locale = Locale.of("vi", "VN");

LunisolarCalendar calendar = LunisolarCalendars.ofDefault();

for (LocalDate solarDate : solarDates) {
    LunisolarDate lunar = calendar.getDate(solarDate, zone);
    LunisolarDateView view =
        LunisolarDateViewFormatter.format(lunar, zone, locale);

    System.out.println(view);
}
```

## Dependency (JitPack)
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.ethanlamtt</groupId>
        <artifactId>viet-chrono</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```
## License
MIT License
