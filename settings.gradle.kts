rootProject.name = "market-pro-backend"
val services = listOf(
    "auth-service",
    "eureka-service",
    "gateway-service",
    "inventory-service",
    "order-service",
    "product-service",
    "user-service"
)

services.forEach { include(it) }

