%dw 2.0
output application/json
---
{
  formatted: payload.timestamp as LocalDateTime as String {format: "yyyy-MM-dd"}
}
