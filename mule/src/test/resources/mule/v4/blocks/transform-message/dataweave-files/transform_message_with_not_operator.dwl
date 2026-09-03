%dw 2.0
output application/json
---
{
  missing: !(payload.name != null),
  present: not (payload.name != null)
}
