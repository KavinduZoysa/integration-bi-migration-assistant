%dw 2.0
output application/json
---
{
  ids: payload.ids joinBy ","
}
