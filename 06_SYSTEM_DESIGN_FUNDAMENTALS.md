# System Design Fundamentals - Scalability, Availability, CAP Theorem

## 🎯 Learning Objectives

By the end of this file, you will:
- Understand system design fundamentals
- Master scalability concepts (vertical vs horizontal)
- Learn availability and reliability principles
- Understand CAP theorem and its implications
- Apply these concepts to real-world system design

## 📊 System Design Overview

```mermaid
graph TD
    A[System Design Fundamentals] --> B[Scalability]
    A --> C[Availability]
    A --> D[Reliability]
    A --> E[CAP Theorem]
    A --> F[Consistency Models]
    
    B --> G[Vertical/Horizontal]
    C --> H[High Availability]
    D --> I[Fault Tolerance]
    E --> J[Trade-offs]
    F --> K[Strong vs Eventual]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style F fill:#ffd1d1
```

## 📈 Scalability

### What is Scalability?

Scalability is the ability of a system to handle growing amounts of work by adding resources to the system.

```mermaid
graph TD
    A[Scalability] --> B[Vertical Scaling]
    A --> C[Horizontal Scaling]
    
    B --> D[Add more power to single machine]
    C --> E[Add more machines to system]
    
    D --> F[CPU upgrade]
    D --> G[Memory upgrade]
    D --> H[Storage upgrade]
    
    E --> I[Load balancing]
    E --> J[Database sharding]
    E --> K[Microservices]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style F fill:#ffd1d1
    style G fill:#ffd1d1
    style H fill:#ffd1d1
    style I fill:#e1ffe1
    style J fill:#e1ffe1
    style K fill:#e1ffe1
```

### Vertical Scaling (Scale Up)

**Definition**: Increasing the capacity of a single server.

```mermaid
graph LR
    A[Server 1] --> B[Upgrade CPU]
    A --> C[Add RAM]
    A --> D[Upgrade Storage]
    
    B --> E[More processing power]
    C --> F[More memory]
    D --> G[More storage]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#e1ffe1
    style F fill:#e1ffe1
    style G fill:#e1ffe1
```

**Advantages:**
- Simpler to implement
- No code changes required
- Lower initial complexity

**Disadvantages:**
- Hardware limits (ceiling)
- Single point of failure
- Expensive for high-end hardware
- Downtime during upgrades

**When to Use:**
- Early stage startups
- Simple applications
- Budget constraints
- Before hitting hardware limits

### Horizontal Scaling (Scale Out)

**Definition**: Adding more servers to handle increased load.

```mermaid
graph TD
    A[Load Balancer] --> B[Server 1]
    A --> C[Server 2]
    A --> D[Server 3]
    A --> E[Server N]
    
    B --> F[Handle 1/N traffic]
    C --> G[Handle 1/N traffic]
    D --> H[Handle 1/N traffic]
    E --> I[Handle 1/N traffic]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style F fill:#e1ffe1
    style G fill:#e1ffe1
    style H fill:#e1ffe1
    style I fill:#e1ffe1
```

**Advantages:**
- Theoretically unlimited scaling
- Better fault tolerance
- Cost-effective (commodity hardware)
- Gradual scaling

**Disadvantages:**
- More complex architecture
- Requires load balancing
- Data consistency challenges
- Higher operational complexity

**When to Use:**
- High traffic applications
- Fault tolerance required
- Growing user base
- Long-term scalability needs

### Load Balancing

**Definition**: Distributing incoming network traffic across multiple servers.

```mermaid
graph TD
    A[Client Request] --> B[Load Balancer]
    B --> C{Routing Algorithm}
    C --> D[Round Robin]
    C --> E[Least Connections]
    C --> F[IP Hash]
    C --> G[Weighted]
    
    D --> H[Server 1]
    E --> I[Server 2]
    F --> J[Server 3]
    G --> K[Server 4]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#fff5e1
    style F fill:#fff5e1
    style G fill:#fff5e1
    style H fill:#e1ffe1
    style I fill:#e1ffe1
    style J fill:#e1ffe1
    style K fill:#e1ffe1
```

**Load Balancing Algorithms:**

1. **Round Robin**: Distributes requests sequentially
2. **Least Connections**: Routes to server with fewest active connections
3. **IP Hash**: Routes based on client IP (session persistence)
4. **Weighted**: Distributes based on server capacity

**Health Checks:**
```mermaid
graph TD
    A[Load Balancer] --> B[Health Check Server 1]
    A --> C[Health Check Server 2]
    A --> D[Health Check Server 3]
    
    B --> E{Healthy?}
    C --> F{Healthy?}
    D --> G{Healthy?}
    
    E -->|Yes| H[Route traffic]
    E -->|No| I[Remove from pool]
    F -->|Yes| J[Route traffic]
    F -->|No| K[Remove from pool]
    G -->|Yes| L[Route traffic]
    G -->|No| M[Remove from pool]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#e1ffe1
    style F fill:#e1ffe1
    style G fill:#e1ffe1
    style H fill:#e1ffe1
    style I fill:#ffd1d1
    style J fill:#e1ffe1
    style K fill:#ffd1d1
    style L fill:#e1ffe1
    style M fill:#ffd1d1
```

## 🟢 Availability

### What is Availability?

Availability is the proportion of time a system is in a functioning condition. It's often expressed as a percentage.

```mermaid
graph TD
    A[Availability Levels] --> B[99%: 7.3 hours/month downtime]
    A --> C[99.9%: 43.2 minutes/month downtime]
    A --> D[99.99%: 4.32 minutes/month downtime]
    A --> E[99.999%: 26 seconds/month downtime]
    
    style A fill:#e1f5ff
    style B fill:#ffd1d1
    style C fill:#ffe1c2
    style D fill:#fff5e1
    style E fill:#e1ffe1
```

### Availability Calculation

**Availability = (Total Time - Downtime) / Total Time × 100%**

**Examples:**
- 99% availability = 7.3 hours downtime per month
- 99.9% availability = 43.2 minutes downtime per month
- 99.99% availability = 4.32 minutes downtime per month
- 99.999% availability = 26 seconds downtime per month

### High Availability Techniques

**Redundancy:**
```mermaid
graph TD
    A[Primary Server] --> B[Backup Server 1]
    A --> C[Backup Server 2]
    A --> D[Backup Server N]
    
    B --> E[Standby mode]
    C --> F[Standby mode]
    D --> G[Standby mode]
    
    H[Primary Fails] --> I[Automatic failover]
    I --> J[Backup takes over]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#ffd1d1
    style F fill:#ffd1d1
    style G fill:#ffd1d1
    style H fill:#ff1a1a
    style I fill:#e1ffe1
    style J fill:#e1ffe1
```

**Failover Mechanisms:**
- **Active-Passive**: One active, others standby
- **Active-Active**: All servers active, share load
- **Geographic Redundancy**: Servers in different locations

### Disaster Recovery

**RPO (Recovery Point Objective)**: Maximum acceptable data loss
**RTO (Recovery Time Objective)**: Maximum acceptable downtime

```mermaid
graph TD
    A[Disaster Occurs] --> B[Detect Failure]
    B --> C[Activate Backup]
    C --> D[Restore Data]
    D --> E[Verify System]
    E --> F[Resume Operations]
    
    style A fill:#ff1a1a
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style F fill:#e1ffe1
```

## 🔧 Reliability

### What is Reliability?

Reliability is the ability of a system to perform its intended function consistently and correctly over time.

```mermaid
graph TD
    A[Reliability Components] --> B[Correctness]
    A --> C[Consistency]
    A --> D[Robustness]
    A --> E[Recoverability]
    
    B --> F[System does what it should]
    C --> G[Same output for same input]
    D --> H[Handles errors gracefully]
    E --> I[Can recover from failures]
    
    style A fill:#e1f5ff
    style B fill:#e1ffe1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
```

### Fault Tolerance

**Definition**: The ability of a system to continue operating properly in the event of failure of some of its components.

```mermaid
graph TD
    A[Component Failure] --> B{System Design}
    B --> C[Fault Tolerant]
    B --> D[Not Fault Tolerant]
    
    C --> E[Detect failure]
    E --> F[Isolate failure]
    F --> G[Recover/Continue]
    
    D --> H[System crashes]
    H --> I[Data loss]
    I --> J[Downtime]
    
    style A fill:#ff1a1a
    style B fill:#e1f5ff
    style C fill:#e1ffe1
    style D fill:#ffd1d1
    style E fill:#c2ffc2
    style F fill:#c2ffc2
    style G fill:#c2ffc2
    style H fill:#ff1a1a
    style I fill:#ff1a1a
    style J fill:#ff1a1a
```

### Redundancy Patterns

**Active-Active Redundancy:**
```mermaid
graph TD
    A[Request] --> B[Load Balancer]
    B --> C[Server 1 Active]
    B --> D[Server 2 Active]
    B --> E[Server 3 Active]
    
    C --> F[Handle traffic]
    D --> G[Handle traffic]
    E --> H[Handle traffic]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#e1ffe1
    style D fill:#e1ffe1
    style E fill:#e1ffe1
    style F fill:#c2ffc2
    style G fill:#c2ffc2
    style H fill:#c2ffc2
```

**Active-Passive Redundancy:**
```mermaid
graph TD
    A[Request] --> B[Primary Server]
    B --> C[Handle traffic]
    
    D[Backup Server] --> E[Standby mode]
    E --> F[Sync with primary]
    
    G[Primary fails] --> H[Backup activates]
    H --> I[Handle traffic]
    
    style A fill:#e1f5ff
    style B fill:#e1ffe1
    style C fill:#c2ffc2
    style D fill:#ffd1d1
    style E fill:#ffd1d1
    style F fill:#ffd1d1
    style G fill:#ff1a1a
    style H fill:#e1ffe1
    style I fill:#c2ffc2
```

## 📐 CAP Theorem

### What is CAP Theorem?

CAP theorem states that a distributed system can only simultaneously provide two out of the following three guarantees:

```mermaid
graph TD
    A[CAP Theorem] --> B[Consistency]
    A --> C[Availability]
    A --> D[Partition Tolerance]
    
    B --> E[All nodes see same data]
    C --> F[System always responds]
    D --> G[System works despite network failure]
    
    H[Choose 2 of 3] --> I[CP: Consistency + Partition Tolerance]
    H --> J[AP: Availability + Partition Tolerance]
    H --> K[CA: Consistency + Availability]
    
    style A fill:#e1f5ff
    style B fill:#e1ffe1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style I fill:#e1ffe1
    style J fill:#c2ffc2
    style K fill:#ffd1d1
```

### Consistency (C)

**Definition**: Every read receives the most recent write or an error.

```mermaid
graph TD
    A[Write to Node 1] --> B[Propagate to Node 2]
    B --> C[Propagate to Node 3]
    C --> D[Consistent state]
    
    E[Read from any node] --> F[Returns latest data]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#e1ffe1
    style E fill:#e1f5ff
    style F fill:#e1ffe1
```

**Examples:**
- Relational databases with strong consistency
- Distributed transactions
- Synchronous replication

### Availability (A)

**Definition**: Every request receives a (non-error) response, without the guarantee that it contains the most recent write.

```mermaid
graph TD
    A[Read request] --> B{Node available?}
    B -->|Yes| C[Return data]
    B -->|No| D[Route to available node]
    D --> E[Return data]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#e1ffe1
    style D fill:#c2ffc2
    style E fill:#e1ffe1
```

**Examples:**
- DNS system
- CDN networks
- NoSQL databases with eventual consistency

### Partition Tolerance (P)

**Definition**: The system continues to operate despite an arbitrary number of messages being dropped or delayed by the network between nodes.

```mermaid
graph TD
    A[Network Partition] --> B[Node 1 isolated]
    A --> C[Node 2 isolated]
    
    B --> D[Continue serving]
    C --> E[Continue serving]
    
    F[Partition heals] --> G[Sync data]
    G --> H[Resolve conflicts]
    
    style A fill:#ff1a1a
    style B fill:#ffd1d1
    style C fill:#ffd1d1
    style D fill:#e1ffe1
    style E fill:#e1ffe1
    style F fill:#c2ffc2
    style G fill:#c2ffc2
    style H fill:#c2ffc2
```

**Examples:**
- All distributed systems must handle partitions
- Network failures are inevitable
- System must decide: CP or AP

### CAP Trade-offs

**CP Systems (Consistency + Partition Tolerance):**
- Prioritize data consistency over availability
- Will reject requests during partitions
- Examples: MongoDB, HBase, Redis

**AP Systems (Availability + Partition Tolerance):**
- Prioritize availability over consistency
- May return stale data during partitions
- Examples: Cassandra, DynamoDB, CouchDB

**CA Systems (Consistency + Availability):**
- Not truly distributed (single node)
- Not possible in distributed systems
- Examples: Single-node databases

```mermaid
graph TD
    A[Network Partition Occurs] --> B{System Choice}
    
    B --> C[CP: Reject writes]
    B --> D[AP: Accept writes]
    
    C --> E[Ensure consistency]
    C --> F[Sacrifice availability]
    
    D --> G[Ensure availability]
    D --> H[Sacrifice consistency]
    
    style A fill:#ff1a1a
    style B fill:#e1f5ff
    style C fill:#e1ffe1
    style D fill:#c2ffc2
    style E fill:#e1ffe1
    style F fill:#ffd1d1
    style G fill:#c2ffc2
    style H fill:#ffd1d1
```

## 🔄 Consistency Models

### Strong Consistency

**Definition**: All clients see the same data at the same time.

```mermaid
graph TD
    A[Write] --> B[Block until all nodes updated]
    B --> C[Confirm write]
    D[Read] --> E[Return latest data]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#e1ffe1
    style D fill:#e1f5ff
    style E fill:#e1ffe1
```

**Advantages:**
- Data is always consistent
- No conflicts to resolve
- Simple to reason about

**Disadvantages:**
- Higher latency
- Lower availability
- More complex to implement

### Eventual Consistency

**Definition**: System guarantees that if no new updates are made, eventually all accesses will return the last updated value.

```mermaid
graph TD
    A[Write to Node 1] --> B[Confirm immediately]
    C[Read from Node 2] --> D[May return stale data]
    E[Time passes] --> F[Data propagates]
    F --> G[All nodes consistent]
    
    style A fill:#e1f5ff
    style B fill:#e1ffe1
    style C fill:#e1f5ff
    style D fill:#ffd1d1
    style E fill:#fff5e1
    style F fill:#c2ffc2
    style G fill:#e1ffe1
```

**Advantages:**
- Low latency
- High availability
- Better scalability

**Disadvantages:**
- May return stale data
- Conflicts to resolve
- Complex to reason about

### Consistency Levels

```mermaid
graph TD
    A[Consistency Spectrum] --> B[Strong]
    A --> C[Weak]
    A --> D[Eventual]
    
    B --> E[All nodes always consistent]
    C --> F[Session consistency]
    D --> G[Eventually consistent]
    
    style A fill:#e1f5ff
    style B fill:#e1ffe1
    style C fill:#ffe1c2
    style D fill:#ffd1d1
    style E fill:#e1ffe1
    style F fill:#ffe1c2
    style G fill:#ffd1d1
```

## 🎯 System Design Selection Guide

```mermaid
graph TD
    A[System Design Decision] --> B{Need strong consistency?}
    A --> C{Need high availability?}
    A --> D{Need low latency?}
    
    B -->|Yes| E[CP System]
    B -->|No| F[AP System]
    
    C -->|Yes| G[Horizontal scaling]
    C -->|No| H[Vertical scaling]
    
    D -->|Yes| I[Eventual consistency]
    D -->|No| J[Strong consistency]
    
    style A fill:#e1f5ff
    style E fill:#e1ffe1
    style F fill:#c2ffc2
    style G fill:#e1ffe1
    style H fill:#ffd1d1
    style I fill:#c2ffc2
    style J fill:#e1ffe1
```

## 🧪 Practice Problems

### Scalability (Day 1-2)
1. **Design URL Shortener**: Handle millions of URLs
2. **Design Web Crawler**: Scale for billions of pages
3. **Design Twitter Timeline**: Handle massive concurrent users

### Availability (Day 3-4)
1. **Design Chat System**: High availability for messaging
2. **Design File Storage**: Geographic redundancy
3. **Design API Gateway**: Load balancing and failover

### CAP Theorem (Day 5-6)
1. **Design Shopping Cart**: Consistency vs availability
2. **Design Leaderboard**: Real-time vs eventual consistency
3. **Design Notification System**: Partition tolerance

## 🎯 Daily Exercises (1-Hour Schedule)

### Day 1: Scalability (10 + 20 + 25 + 5)
**Learning (10 min):**
- Read scalability section
- Study vertical vs horizontal scaling
- Understand load balancing

**Examples (20 min):**
- Design a scalable URL shortener
- Practice load balancing strategies
- Understand database sharding

**Practice (25 min):**
- Design URL shortener architecture
- Calculate capacity requirements
- Plan scaling strategy

**Review (5 min):**
- Review scaling decisions
- Note trade-offs you considered

### Day 2: Availability (10 + 20 + 25 + 5)
**Learning (10 min):**
- Read availability section
- Study redundancy patterns
- Understand failover mechanisms

**Examples (20 min):**
- Design high availability system
- Practice disaster recovery planning
- Understand health checks

**Practice (25 min):**
- Design high availability chat system
- Plan failover strategy
- Calculate availability targets

**Review (5 min):**
- Review availability techniques
- Note which patterns you understood

### Day 3: CAP Theorem (10 + 20 + 25 + 5)
**Learning (10 min):**
- Read CAP theorem section
- Understand consistency models
- Study trade-offs

**Examples (20 min):**
- Analyze systems for CAP properties
- Practice choosing CP vs AP
- Understand consistency levels

**Practice (25 min):**
- Design shopping cart system
- Choose consistency model
- Justify CAP trade-offs

**Review (5 min):**
- Review CAP implications
- Note when to choose each property

### Day 4-6: Mixed Practice (10 + 20 + 25 + 5)
**Learning (10 min):**
- Review all concepts
- Study selection guide
- Understand hybrid approaches

**Examples (20 min):**
- Practice combining concepts
- Study real-world systems
- Understand architectural decisions

**Practice (25 min):**
- Design complete systems
- Apply all concepts
- Document trade-offs

**Review (5 min):**
- Review your design decisions
- Note areas that need more practice

## 📋 Weekly Summary

### Week 6 Goals
- [ ] Understand scalability principles
- [ ] Design highly available systems
- [ ] Apply CAP theorem to system design
- [ ] Choose appropriate consistency models
- [ ] Document architectural trade-offs

### Week 6 Checklist
- [ ] Completed all daily exercises
- [ ] Can design scalable systems
- [ ] Can implement high availability
- [ ] Can apply CAP theorem
- [ ] Can choose consistency models
- [ ] Ready to move to system design patterns

## 🚀 Next Steps

After mastering system design fundamentals:
1. **Move to File 07**: System Design Patterns
2. **Apply these concepts** to specific design patterns
3. **Practice real-world system design** interviews
4. **Learn advanced techniques** for complex systems

## 💡 Key Takeaways

1. **Scalability** requires choosing between vertical and horizontal scaling
2. **Availability** is achieved through redundancy and failover
3. **CAP theorem** forces trade-offs in distributed systems
4. **Consistency models** range from strong to eventual
5. **System design** is about making informed trade-offs

---

**You're now ready for system design patterns!** → [07_SYSTEM_DESIGN_PATTERNS.md](./07_SYSTEM_DESIGN_PATTERNS.md)
