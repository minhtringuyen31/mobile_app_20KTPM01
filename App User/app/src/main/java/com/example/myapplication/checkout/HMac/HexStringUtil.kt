package com.example.myapplication.checkout.HMac



import java.util.Locale

object HexStringUtil {
    private val HEX_CHAR_TABLE = byteArrayOf(
        '0'.toByte(), '1'.toByte(), '2'.toByte(), '3'.toByte(),
        '4'.toByte(), '5'.toByte(), '6'.toByte(), '7'.toByte(),
        '8'.toByte(), '9'.toByte(), 'a'.toByte(), 'b'.toByte(),
        'c'.toByte(), 'd'.toByte(), 'e'.toByte(), 'f'.toByte()
    )

    /**
     * Convert a byte array to a hexadecimal string
     *
     * @param raw A raw byte array
     *
     * @return Hexadecimal string
     */
    fun byteArrayToHexString(raw: ByteArray): String {
        val hex = ByteArray(2 * raw.size)
        var index = 0

        for (b in raw) {
            val v = b.toInt() and 0xFF
            hex[index++] = HEX_CHAR_TABLE[v.ushr(4)]
            hex[index++] = HEX_CHAR_TABLE[v and 0xF]
        }
        return String(hex)
    }

    /**
     * Convert a hexadecimal string to a byte array
     *
     * @param hex A hexadecimal string
     *
     * @return The byte array
     */
    fun hexStringToByteArray(hex: String): ByteArray {
        val hexStandard = hex.toLowerCase(Locale.ENGLISH)
        val sz = hexStandard.length / 2
        val bytesResult = ByteArray(sz)

        var idx = 0
        for (i in 0 until sz) {
            bytesResult[i] = hexStandard[idx++].toByte()
            val tmp = hexStandard[idx++].toByte()

            if (bytesResult[i] > HEX_CHAR_TABLE[9]) {
                bytesResult[i] = (bytesResult[i] - ('a'.toByte() - 10)).toByte()
            } else {
                bytesResult[i] = (bytesResult[i] - '0'.toByte()).toByte()
            }
            if (tmp > HEX_CHAR_TABLE[9]) {
                bytesResult[i] = (bytesResult[i] * 16 + (tmp - ('a'.toByte() - 10))).toByte()
            } else {
                bytesResult[i] = (bytesResult[i] * 16 + (tmp - '0'.toByte())).toByte()
            }
        }
        return bytesResult
    }
}