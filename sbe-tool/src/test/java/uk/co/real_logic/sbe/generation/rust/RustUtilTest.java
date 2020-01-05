package uk.co.real_logic.sbe.generation.rust;

import org.junit.jupiter.api.Test;
import uk.co.real_logic.sbe.PrimitiveType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static uk.co.real_logic.sbe.generation.rust.RustUtil.formatMethodName;
import static uk.co.real_logic.sbe.generation.rust.RustUtil.generateRustLiteral;

public class RustUtilTest
{
    @Test
    public void nullParamToEightBitCharacterThrowsNPE()
    {
        assertThrows(NullPointerException.class, () ->
            RustUtil.eightBitCharacter(null));
    }

    @Test
    public void emptyParamToEightBitCharacterThrowsIAE()
    {
        assertThrows(IllegalArgumentException.class, () ->
            RustUtil.eightBitCharacter(""));
    }

    @Test
    public void tooManyCharactersParamToEightBitCharacterThrowsIAE()
    {
        assertThrows(IllegalArgumentException.class, () ->
            RustUtil.eightBitCharacter("ABC"));
    }

    @Test
    public void happyPathEightBitCharacter()
    {
        final byte aByte = RustUtil.eightBitCharacter("a");
        assertEquals('a', (char)aByte);
        assertEquals("97", Byte.toString(aByte));
    }

    @Test
    public void generateRustLiteralsHappyPaths()
    {
        assertEquals("65i8", generateRustLiteral(PrimitiveType.CHAR, "65"));
        assertEquals("64.1f64", generateRustLiteral(PrimitiveType.DOUBLE, "64.1"));
        assertEquals("f64::NAN", generateRustLiteral(PrimitiveType.DOUBLE, "NaN"));
        assertEquals("64.1f32", generateRustLiteral(PrimitiveType.FLOAT, "64.1"));
        assertEquals("f32::NAN", generateRustLiteral(PrimitiveType.FLOAT, "NaN"));
        assertEquals("65i8", generateRustLiteral(PrimitiveType.INT8, "65"));
        assertEquals("65i16", generateRustLiteral(PrimitiveType.INT16, "65"));
        assertEquals("65i32", generateRustLiteral(PrimitiveType.INT32, "65"));
        assertEquals("65i64", generateRustLiteral(PrimitiveType.INT64, "65"));
        assertEquals("65u8", generateRustLiteral(PrimitiveType.UINT8, "65"));
        assertEquals("65u16", generateRustLiteral(PrimitiveType.UINT16, "65"));
        assertEquals("65u32", generateRustLiteral(PrimitiveType.UINT32, "65"));
        assertEquals("65u64", generateRustLiteral(PrimitiveType.UINT64, "65"));
    }

    @Test
    public void generateRustLiteralNullPrimitiveTypeParam()
    {
        assertThrows(NullPointerException.class, () -> generateRustLiteral(null, "65"));
    }

    @Test
    public void generateRustLiteralNullValueParam()
    {
        assertThrows(NullPointerException.class, () -> generateRustLiteral(PrimitiveType.INT8, null));
    }

    @Test
    public void methodNameCasing()
    {
        assertEquals("", formatMethodName(""));
        assertEquals("a", formatMethodName("a"));
        assertEquals("a", formatMethodName("A"));
        assertEquals("car", formatMethodName("Car"));
        assertEquals("car", formatMethodName("car"));
        assertEquals("decode_car", formatMethodName("DecodeCar"));
        assertEquals("decode_car", formatMethodName("decodeCar"));
        assertEquals("decode_car", formatMethodName("decode_car"));
        assertEquals("decode_car", formatMethodName("Decode_car"));
        assertEquals("decode_car", formatMethodName("decode_Car"));
        assertEquals("decode_car", formatMethodName("Decode_Car"));
    }
}
