%dw 2.0
output application/json

/**
 * Builds the response object.
 * Comments must not reach the generated code.
 */
var total = 10
---
{
  /* the running total */
  total: total,
  label: "count" // trailing line comment
}
