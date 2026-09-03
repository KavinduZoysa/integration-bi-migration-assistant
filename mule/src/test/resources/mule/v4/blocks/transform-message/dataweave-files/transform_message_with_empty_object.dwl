%dw 2.0
output application/json
---
{
  attributes: {},
  fallback: payload.details default {}
}
