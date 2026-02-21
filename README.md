## Overview
An implementation of the Vietnamese lunisolar calendar based on Meeus' astronomical algorithms.
This library supports to convert a solar date to lunisolar date.

## A Lunisolar date's components
A lunisolar date's components include:
- Lunar date corresponding to the solar date.
- Solar term of the day and the transition moment of that term.
- Sexagenary date (cyclic year, month, day and hour).
- List of auspicious hours of the day.
- List of holidays of the solar or lunar date.
## Quick start
```java
// Defines a list of solar dates. 
LocalDate[] solarDates = {
    LocalDate.of(2025, 7, 24),
    LocalDate.of(2027, 2, 10),
    LocalDate.of(2028, 2, 26)
};

// Defines the time zone rule.
ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
// Defines the locale for i18n.
Locale locale = Locale.of("vi", "VN");

// Gets the default instance of the calendar system.
LunisolarCalendar calendar = LunisolarCalendars.ofDefault();

// For each solar date, we convert it to a lunisolar date
// and format it according to the specified locale.
for (LocalDate solarDate : solarDates) {
    // Converts a solar date to a lunisolar date.
    LunisolarDate lunisolarDate = calendar.getDate(solarDate, zoneId);
    LunisolarDateView view =
        LunisolarDateViewFormatter.format(lunisolarDate, zoneId, locale);

    // Now, the lunisolar date will be displayed in the specified locale.
    System.out.println(view);
}
```
## Installation
For maven projects:
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

## Usage
### Get a calendar system instance
Each calendar system instance has a unique id. The representation of the calendar system is
**LunisolarCalendar** interface and its factory is **LunisolarCalendars** (with the *s* posfix).
In order to get a calendar instance we need to invoke the *of* method from the factory.
```java
// Gets the default calendar system instance  
LunisolarCalendar calendar = LunisolarCalendars.ofDefault();

// Gets the specified calendar system instance based on
// the registered id.
// This would throw an IllegalArgumentException 
// if there is no calendar with the "HoNgocDuc" id. 
LunisolarCalendar calendar = LunisolarCalendars.of("HoNgocDuc");
```
Note that all registered instances are instance-controlled.

### Convert a solar date to a lunisolar date 
In Vietnam history, the timezone has not always been UTC+7, so the result 
may differ from other implementations.
```java 

// Defines time zone rules.
ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
// Defines a solar date to be converted.
LocalDate solarDate = LocalDate.of(2026, 2, 21);

// We assume that the calendar instance has been provided.
LunisolarDate lunisolarDate = calendar.getDate(solarDate, zoneId);

System.out.println(lunisolarDate);
```
The output:
```java
LunisolarDate(
    solarDate=2026-02-21
    lunarDate=LunarDate(year=2026, month=1, day=5, isLeapYear=false)
    dailySolarTerm=DailySolarTerm(termOfDay=RAIN_WATER, transition=null)
    ...
)
```

### Find a lunar phase occurring *before* or *after* a specified moment
A moment is an astronomical instant (seems like **Instant** in java.time).
This used for converting between **Instant** and **JulianDay**. In order to
find a lunar phase occurring before a specified moment we need to define
a solar date, convert it to a moment and pass that **Moment** to **LunarTime.before(Moment, LunarPhase)**. 
```java
ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
// Defines a solar date with a timezone.
ZonedDateTime solarDate = LocalDate.of(2026, 2, 21)
                .atStartOfDay(zoneId);
// Converts the solar date to a moment.
Moment moment = Moment.ofInstant(
        solarDate.toInstant()
);

// Gets the lunar time calculation instance.
LunarTime lunarTime = LunarTime.getInstance();
// Invokes the before method to get the result.
Moment resultMoment = lunarTime.before(moment, LunarPhase.NEW_MOON);
// Converts the result moment to a solar date.
ZonedDateTime result = resultMoment.toInstant()
        .atZone(zoneId);

// Prints it and to see the result. 
// The correct result is 17 February, 2026 19:01 UTC+7
// from https://www.timeanddate.com/moon/phases/vietnam/ho-chi-minh?year=2026
System.out.println(result);
```
The output:
```java
2026-02-17T19:01:29+07:00[Asia/Ho_Chi_Minh]
```
**LunarTime.after(Moment, LunarPhase)** method is similar the method above.  

### Find an apparent ecliptic longitude of the Sun at an observation moment
```java
// Defines a solar date with a timezone.
ZonedDateTime solarDate = LocalDate.of(2026, 2, 21)
        .atStartOfDay(UTC);
// Converts the solar date to a moment.
Moment moment = Moment.ofInstant(
        solarDate.toInstant()
);

// Gets the solar time position calculation instance.
SolarTime solarTime = SolarTime.getInstance();
// Invokes the apparentLongitudeAt method to get the result.
double longitude = solarTime.apparentLongitudeAt(moment);

// Prints it and to see the result.
// The correct result is
/*
 Date__(UT)__HR:MN Date_________JDUT        ObsEcLon    ObsEcLat
 ****************************************************************
 $$SOE
 2026-Feb-21 00:00 2461092.500000000     332.3589195   0.0001072
 */
// from https://ssd.jpl.nasa.gov/horizons/app.html#/
System.out.println(longitude);
```
The output:
```java
332.3588680348712
```
### Gets the value of deltaT
DeltaT is a time difference between two Terrestrial Time (TT) and
Universal Time (UT1) defined as DeltaT = TT - UT1. Because |UT1 - UTC| <= 0.9s, so
we consider UTC ≈ UT1
```java
// Gets the deltaT calculation instance.
DeltaT deltaT = DeltaT.getInstance();

// Invokes the atYear method to get the result.
double result = deltaT.atYear(2000.0);

// Prints it and to see the result.
// The correct result is +63.8
// from https://eclipse.gsfc.nasa.gov/LEcat5/deltat.html
System.out.println(result);
```
The output:
```java
63.86
```
## License
MIT License
