%dw 2.0
output application/json
import buildIds from dw::common::utils

var items = if (payload != null) [payload] else []
---
{
  items: items,
  empty: null
}
