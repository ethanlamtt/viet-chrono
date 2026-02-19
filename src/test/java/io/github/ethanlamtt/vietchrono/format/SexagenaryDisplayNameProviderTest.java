package io.github.ethanlamtt.vietchrono.format;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.*;
import static io.github.ethanlamtt.vietchrono.sexagenary.HeavenlyStem.*;
import static org.junit.jupiter.api.Assertions.*;

public class SexagenaryDisplayNameProviderTest {
    @Test
    void testGetInstance() {
        SexagenaryDisplayNameProvider a = SexagenaryDisplayNameProvider.getInstance();
        SexagenaryDisplayNameProvider b = SexagenaryDisplayNameProvider.getInstance();
        assertNotNull(a);
        assertSame(a, b);
    }

    @Test
    void testGetStemNameVi() {
        SexagenaryDisplayNameProvider a = SexagenaryDisplayNameProvider.getInstance();
        Locale vn = Locale.of("vi", "VN");
        assertEquals("Giáp", a.getStemName(YANG_WOOD, vn));
        assertEquals("Ất", a.getStemName(YIN_WOOD, vn));
        assertEquals("Bính", a.getStemName(YANG_FIRE, vn));
        assertEquals("Đinh", a.getStemName(YIN_FIRE, vn));
        assertEquals("Mậu", a.getStemName(YANG_EARTH, vn));
        assertEquals("Kỷ", a.getStemName(YIN_EARTH, vn));
        assertEquals("Canh", a.getStemName(YANG_METAL, vn));
        assertEquals("Tân", a.getStemName(YIN_METAL, vn));
        assertEquals("Nhâm", a.getStemName(YANG_WATER, vn));
        assertEquals("Quý", a.getStemName(YIN_WATER, vn));
    }

    @Test
    void testGetBranchNameVi() {
        SexagenaryDisplayNameProvider a = SexagenaryDisplayNameProvider.getInstance();
        Locale vn = Locale.of("vi", "VN");
        assertEquals("Tý", a.getBranchName(RAT, vn));
        assertEquals("Sửu", a.getBranchName(WATER_BUFFALO, vn));
        assertEquals("Dần", a.getBranchName(TIGER, vn));
        assertEquals("Mẹo", a.getBranchName(CAT, vn));
        assertEquals("Thìn", a.getBranchName(DRAGON, vn));
        assertEquals("Tỵ", a.getBranchName(SNAKE, vn));
        assertEquals("Ngọ", a.getBranchName(HORSE, vn));
        assertEquals("Mùi", a.getBranchName(GOAT, vn));
        assertEquals("Thân", a.getBranchName(MONKEY, vn));
        assertEquals("Dậu", a.getBranchName(ROOSTER, vn));
        assertEquals("Tuất", a.getBranchName(DOG, vn));
        assertEquals("Hợi", a.getBranchName(PIG, vn));
    }

    @Test
    void testGetStemNameEn() {
        SexagenaryDisplayNameProvider a = SexagenaryDisplayNameProvider.getInstance();
        Locale en = Locale.ENGLISH;
        assertEquals("Yang Wood", a.getStemName(YANG_WOOD, en));
        assertEquals("Yin Wood", a.getStemName(YIN_WOOD, en));
        assertEquals("Yang Fire", a.getStemName(YANG_FIRE, en));
        assertEquals("Yin Fire", a.getStemName(YIN_FIRE, en));
        assertEquals("Yang Earth", a.getStemName(YANG_EARTH, en));
        assertEquals("Yin Earth", a.getStemName(YIN_EARTH, en));
        assertEquals("Yang Metal", a.getStemName(YANG_METAL, en));
        assertEquals("Yin Metal", a.getStemName(YIN_METAL, en));
        assertEquals("Yang Water", a.getStemName(YANG_WATER, en));
        assertEquals("Yin Water", a.getStemName(YIN_WATER, en));
    }

    @Test
    void testGetBranchNameEn() {
        SexagenaryDisplayNameProvider a = SexagenaryDisplayNameProvider.getInstance();
        Locale en = Locale.ENGLISH;
        assertEquals("Rat", a.getBranchName(RAT, en));
        assertEquals("Water Buffalo", a.getBranchName(WATER_BUFFALO, en));
        assertEquals("Tiger", a.getBranchName(TIGER, en));
        assertEquals("Cat", a.getBranchName(CAT, en));
        assertEquals("Dragon", a.getBranchName(DRAGON, en));
        assertEquals("Snake", a.getBranchName(SNAKE, en));
        assertEquals("Horse", a.getBranchName(HORSE, en));
        assertEquals("Goat", a.getBranchName(GOAT, en));
        assertEquals("Monkey", a.getBranchName(MONKEY, en));
        assertEquals("Rooster", a.getBranchName(ROOSTER, en));
        assertEquals("Dog", a.getBranchName(DOG, en));
        assertEquals("Pig", a.getBranchName(PIG, en));
    }

    @Test
    void testGetNameCn() {
        SexagenaryDisplayNameProvider a = SexagenaryDisplayNameProvider.getInstance();
        assertThrows(IllegalArgumentException.class, () -> a.getStemName(YANG_WOOD, Locale.CHINESE));
        assertThrows(IllegalArgumentException.class, () -> a.getBranchName(RAT, Locale.CHINESE));
    }
}
