%dw 2.0
output application/json
import config, mapRecord from dw::common::utils
---
{ id: payload.id }
