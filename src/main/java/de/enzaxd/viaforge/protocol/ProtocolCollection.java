package de.enzaxd.viaforge.protocol;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;

public enum ProtocolCollection {
	
    // 1.19.x  
    R1_19_3(new ProtocolVersion(761, "1.19.3")),
    R1_19_2(new ProtocolVersion(760, "1.19.1/2")),
    R1_19(new ProtocolVersion(759, "1.19")),
    // 1.18.x
    R1_18_2(new ProtocolVersion(758, "1.18.2")),
    R1_18_1(new ProtocolVersion(757, "1.18/1")),
    // 1.17.x
    R1_17_1(new ProtocolVersion(756, "1.17.1")),
    R1_17(new ProtocolVersion(755, "1.17")),
    // 1.16.x 
    R1_16_5(new ProtocolVersion(754, "1.16.4/5")),
    R1_16_3(new ProtocolVersion(753, "1.16.3")),
    R1_16_2(new ProtocolVersion(751, "1.16.2")),
    R1_16_1(new ProtocolVersion(736, "1.16.1")),
    R1_16(new ProtocolVersion(735, "1.16")),
    // 1.15.x
    R1_15_2(new ProtocolVersion(578, "1.15.2")),
    R1_15_1(new ProtocolVersion(575, "1.15.1")),
    R1_15(new ProtocolVersion(573, "1.15")),
    // 1.14.x   
    R1_14_4(new ProtocolVersion(498, "1.14.4")),
    R1_14_3(new ProtocolVersion(490, "1.14.3")),
    R1_14_2(new ProtocolVersion(485, "1.14.2")),
    R1_14_1(new ProtocolVersion(480, "1.14.1")),
    R1_14(new ProtocolVersion(477, "1.14")),
    // 1.13.x
    R1_13_2(new ProtocolVersion(404, "1.13.2")),
    R1_13_1(new ProtocolVersion(401, "1.13.1")),
    R1_13(new ProtocolVersion(393, "1.13")),
    // 1.12.x
    R1_12_2(new ProtocolVersion(340, "1.12.2")),
    R1_12_1(new ProtocolVersion(338, "1.12.1")),
    R1_12(new ProtocolVersion(335, "1.12")),
    // 1.11.x
    R1_11_1(new ProtocolVersion(316, "1.11.1/2")),
    R1_11(new ProtocolVersion(315, "1.11")),
    // 1.10
    R1_10(new ProtocolVersion(210, "1.10.x")),
    // 1.9.x
    R1_9_4(new ProtocolVersion(110, "1.9.3/4")),
    R1_9_2(new ProtocolVersion(109, "1.9.2")),
    R1_9_1(new ProtocolVersion(108, "1.9.1")),
    R1_9(new ProtocolVersion(107, "1.9")),
    // 1.8
    R1_8(new ProtocolVersion(47, "1.8.x"));

    private ProtocolVersion version;

    private ProtocolCollection(ProtocolVersion version) {
        this.version = version;
    }

    public ProtocolVersion getVersion() {
        return version;
    }

    public static ProtocolVersion getProtocolById(int id) {
        for (ProtocolCollection coll : values())
            if (coll.getVersion().getVersion() == id)
                return coll.getVersion();
        return null;
    }
}
