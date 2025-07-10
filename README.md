# Expense Sheet System

A Grails-based expense tracking system that allows users to track expenses with automatic ZAR to USD conversion.

## Features

- User account creation with starting ZAR balance
- Expense transaction tracking
- Real-time running balance calculations
- ZAR to USD currency conversion via fixer.io API
- CSV export of all transactions
- Comprehensive test suite

## Technology Stack

- **Framework:** Grails 6.2.3
- **Database:** H2 (in-memory)
- **Testing:** Spock Framework
- **External API:** fixer.io for currency conversion

## Getting Started

### Prerequisites

- Java 17+
- Git

### Installation & Running

1. Clone the repository:

```bash
git clone https://github.com/Afk-Marcel/expense-sheet-system.git
cd expense-sheet-systems
```

2. Run the application:

```bash
./gradlew run
```

3. Access the application:

- Open your browser to http://localhost:8080
- Create your first user account
- Start tracking expenses!

## Testing

### Run Integration Tests

```bash
./gradlew integrationTest
```

### Run All Tests

```bash
./gradlew test integrationTest
```

## Usage

1. First Time Setup: Visit /home to create your user account
2. Add Transactions: Navigate to /transaction/create
3. View History: Visit /transaction to see all transactions
4. Export Data: Use /transaction/exportCsv?userId=1 to download CSV

## Performance Optimizations for 10k Concurrent Users

When scaling to 10,000 concurrent users, the following optimizations would be essential:

### Database Optimizations

- Replace H2 with PostgreSQL/MySQL for production-grade persistence
- Connection pooling with HikariCP (increase pool size to 50-100)
- Database indexing on frequently queried fields (user.name, transaction.dateCreated)
- Read replicas for transaction queries to distribute load

### Caching Strategy

- Redis cache for exchange rates (extend cache time to 4-6 hours)
- Application-level caching for user sessions
- CDN for static assets

### Application Architecture

- Horizontal scaling with load balancer (multiple Grails instances)
- Async processing for CSV generation (use Grails async features)
- Message queues for non-critical operations
- Separate microservice for currency conversion to reduce API rate limits

### API Rate Limiting

- Implement user-level rate limiting for CSV exports
- Batch currency API calls to reduce fixer.io requests
- Fallback exchange rates during API outages

### Monitoring & Performance

- APM tools (New Relic, DataDog) for performance monitoring
- Database query optimization with query analysis
- JVM tuning for garbage collection optimization
- Circuit breakers for external API calls

### Security Considerations

- Authentication/Authorization (Spring Security)
- HTTPS termination at load balancer
- Input validation and SQL injection prevention
- Rate limiting to prevent abuse

### Architecture Overview

┌─────────────────────────────────────────────────────────┐
│ PRESENTATION LAYER │
│ ┌─────────────┬─────────────┬───────────────────────┐ │
│ │HomeController│UserController│TransactionController │ │
│ └─────────────┴─────────────┴───────────────────────┘ │
└─────────────────────────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────┐
│ SERVICE LAYER │
│ ┌─────────────┬─────────────┬─────────────────────────┐ │
│ │ UserService │TransactionSvc│ CurrencyService │ │
│ └─────────────┴─────────────┴─────────────────────────┘ │
└─────────────────────────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────┐
│ DOMAIN LAYER │
│ ┌─────────────────────┬─────────────────────────────┐ │
│ │ User Domain │ Transaction Domain │ │
│ │ - name │ - amount │ │
│ │ - startingBalance │ - description │ │
│ │ - currentBalance │ - runningBalance │ │
│ │ - dateCreated │ - dateCreated │ │
│ │ │ - usdAmount (transient) │ │
│ └─────────────────────┴─────────────────────────────┘ │
└─────────────────────────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────┐
│ DATA ACCESS LAYER │
│ GORM + H2 │
└─────────────────────────────────────────────────────────┘
│
▼
┌─────────────────────────┐
│ EXTERNAL SERVICES │
│ fixer.io API │
│ Currency Conversion │
└─────────────────────────┘

### Testing Strategy

- Integration Tests: Full application context with real database
- Service Tests: Business logic validation with mocked dependencies
- Currency Service: API integration testing with fallback scenarios

**Developed by:** Afk-Marcel
**Contact:** mjgreyling@icloud.com
