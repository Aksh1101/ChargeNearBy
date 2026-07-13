package com.aksh.chargenearby.domain.model



enum class ConnectorType(
    val displayName: String
) {
    CCS2("CCS2"),
    TYPE_2("Type 2"),
    CHADEMO("CHAdeMO"),
    GB_T("GB/T"),
    BHARAT_AC_001("Bharat AC-001"),
    BHARAT_DC_001("Bharat DC-001")
}