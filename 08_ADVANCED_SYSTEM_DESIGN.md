# Advanced System Design - Distributed Systems, Messaging, Real-time Systems

## 🎯 Learning Objectives

By the end of this file, you will:
- Master distributed system concepts
- Understand message queue patterns
- Learn real-time system design
- Apply advanced techniques to complex systems
- Design large-scale distributed architectures

## 📊 Advanced System Design Overview

```mermaid
graph TD
    A[Advanced System Design] --> B[Distributed Systems]
    A --> C[Message Queues]
    A --> D[Real-time Systems]
    A --> E[Microservices]
    A --> F[Event-Driven Architecture]
    
    B --> G[Consensus & Coordination]
    C --> H[Patterns & Use Cases]
    D --> I[WebSockets & Streaming]
    E --> J[Service Communication]
    F --> K[Event Sourcing & CQRS]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style F fill:#ffd1d1
```

## 🌐 Distributed Systems

### What are Distributed Systems?

Distributed systems are collections of independent computers that appear to users as a single coherent system.

```mermaid
graph TD
    A[Client] --> B[Load Balancer]
    B --> C[Service A]
    B --> D[Service B]
    B --> E[Service C]
    
    C --> F[Database 1]
    D --> G[Database 2]
    E --> H[Database 3]
    
    I[Message Queue] --> J[Async Communication]
    J --> C
    J --> D
    J --> E
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#e1ffe1
    style D fill:#c2ffc2
    style E fill:#fff5e1
    style I fill:#f5e1ff
    style J fill:#c2ffc2
```

### Distributed System Challenges

**Network Issues:**
- Latency and jitter
- Packet loss
- Network partitions
- Unreliable communication

**Consistency Issues:**
- Data synchronization
- Conflict resolution
- Distributed transactions
- Consistency models

**Coordination Issues:**
- Leader election
- Distributed locking
- Consensus algorithms
- Failure detection

### Consensus Algorithms

#### Paxos

**Description**: Family of protocols for solving consensus in a network of unreliable processors.

```mermaid
graph TD
    A[Proposer] --> B[Prepare Phase]
    B --> C[Acceptors respond]
    C --> D[Accept Phase]
    D --> E[Acceptors accept]
    E --> F[Consensus reached]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#e1ffe1
    style F fill:#e1ffe1
```

**Phases:**
1. **Prepare**: Proposer sends prepare request to acceptors
2. **Promise**: Acceptors promise not to accept other proposals
3. **Accept**: Proposer sends accept request
4. **Accepted**: Acceptors accept the proposal

**When to Use:**
- Need strong consistency
- Can tolerate higher latency
- Critical consensus operations

#### Raft

**Description**: Consensus algorithm designed to be understandable.

```mermaid
graph TD
    A[Leader] --> B[Append Entries]
    B --> C[Followers]
    C --> D[Respond]
    D --> E[Commit if majority]
    
    F[Leader Election] --> G[Timeout]
    G --> H[Request Votes]
    H --> I[Become Leader]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#e1ffe1
    style F fill:#f5e1ff
    style G fill:#ffd1d1
    style H fill:#ffd1d1
    style I fill:#e1ffe1
```

**States:**
- **Leader**: Handles all client requests
- **Follower**: Respond to leader requests
- **Candidate**: Transient state during election

**When to Use:**
- Need understandable consensus
- Leader-based architecture
- Fault tolerance required

### Distributed Locking

**What is Distributed Locking?**
Mechanism to provide mutual exclusion across distributed systems.

```mermaid
graph TD
    A[Service 1] --> B[Request Lock]
    A2[Service 2] --> C[Request Lock]
    
    B --> D{Lock Available?}
    C --> D
    
    D -->|Yes| E[Grant to Service 1]
    D -->|No| F[Service 2 waits]
    
    E --> G[Service 1 completes]
    G --> H[Release Lock]
    H --> I[Grant to Service 2]
    
    style A fill:#e1f5ff
    style A2 fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#ffe1e1
    style D fill:#c2ffc2
    style E fill:#e1ffe1
    style F fill:#ffd1d1
    style G fill:#e1ffe1
    style H fill:#c2ffc2
    style I fill:#e1ffe1
```

**Implementation Approaches:**
1. **Database-based**: Using database locks
2. **Redis-based**: Using Redis SETNX
3. **ZooKeeper-based**: Using ZooKeeper ephemeral nodes
4. **Etcd-based**: Using etcd transactions

**Example (Redis):**
```python
def acquire_lock(lock_key, timeout=10):
    # Try to acquire lock
    result = redis.set(lock_key, "locked", nx=True, ex=timeout)
    return result is not None

def release_lock(lock_key):
    # Release lock
    redis.delete(lock_key)
```

## 📨 Message Queues

### What are Message Queues?

Message queues enable asynchronous communication between services by decoupling message senders from receivers.

```mermaid
graph TD
    A[Producer 1] --> B[Message Queue]
    C[Producer 2] --> B
    D[Producer 3] --> B
    
    B --> E[Consumer 1]
    B --> F[Consumer 2]
    B --> G[Consumer 3]
    
    style A fill:#e1f5ff
    style C fill:#e1f5ff
    style D fill:#e1f5ff
    style B fill:#ffe1e1
    style E fill:#c2ffc2
    style F fill:#c2ffc2
    style G fill:#c2ffc2
```

### Message Queue Patterns

#### Point-to-Point

**Description**: Each message consumed by single consumer.

```mermaid
graph TD
    A[Producer] --> B[Queue]
    B --> C[Consumer 1]
    B --> D[Consumer 2]
    B --> E[Consumer 3]
    
    F[Message 1] --> G[Consumed by Consumer 1]
    H[Message 2] --> I[Consumed by Consumer 2]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#e1ffe1
    style D fill:#c2ffc2
    style E fill:#fff5e1
    style F fill:#e1f5ff
    style G fill:#e1ffe1
    style H fill:#e1f5ff
    style I fill:#c2ffc2
```

**Use Cases:**
- Task queues
- Job processing
- Order processing

#### Publish-Subscribe

**Description**: Each message consumed by multiple consumers.

```mermaid
graph TD
    A[Producer] --> B[Topic]
    B --> C[Subscription 1]
    B --> D[Subscription 2]
    B --> E[Subscription 3]
    
    C --> F[Consumer 1]
    D --> G[Consumer 2]
    E --> H[Consumer 3]
    
    I[Message] --> J[All consumers receive]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#e1ffe1
    style D fill:#c2ffc2
    style E fill:#fff5e1
    style F fill:#e1ffe1
    style G fill:#c2ffc2
    style H fill:#fff5e1
    style I fill:#e1f5ff
    style J fill:#e1ffe1
```

**Use Cases:**
- Event notification
- Real-time updates
- Log aggregation

#### Request-Reply

**Description**: Synchronous-like communication over async queue.

```mermaid
graph TD
    A[Client] --> B[Request Queue]
    B --> C[Service]
    C --> D[Response Queue]
    D --> E[Client]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#e1ffe1
```

**Use Cases:**
- Remote procedure calls
- Query-response patterns
- Synchronous over async

### Message Queue Implementations

**RabbitMQ:**
- Feature-rich message broker
- Supports multiple protocols
- Flexible routing
- Good for complex routing

**Apache Kafka:**
- Distributed streaming platform
- High throughput
- Persistent storage
- Good for event streaming

**Amazon SQS:**
- Fully managed service
- Simple to use
- Auto-scaling
- Good for AWS environments

**Redis Streams:**
- Lightweight
- Built into Redis
- Simple API
- Good for simple use cases

### Message Queue Use Cases

**Asynchronous Processing:**
```python
# Producer
def send_email(email_data):
    queue.publish('email_queue', email_data)

# Consumer
def process_email():
    message = queue.consume('email_queue')
    send_email(message.data)
```

**Event Sourcing:**
```python
# Event publisher
def publish_event(event):
    queue.publish('events', event)

# Event subscriber
def handle_event(event):
    if event.type == 'USER_CREATED':
        create_user_profile(event.data)
    elif event.type == 'ORDER_PLACED':
        process_order(event.data)
```

**Rate Limiting:**
```python
# Rate limiting with queue
def process_request(request):
    queue.publish('requests', request)
    
def rate_limited_consumer():
    while True:
        request = queue.consume('requests')
        process(request)
        sleep(1)  # Rate limit
```

## ⚡ Real-time Systems

### What are Real-time Systems?

Real-time systems process data and provide responses within strict time constraints.

```mermaid
graph TD
    A[Real-time Communication] --> B[WebSockets]
    A --> C[Server-Sent Events]
    A --> D[WebRTC]
    
    B --> E[Bidirectional]
    C --> F[Server to Client]
    D --> G[Peer to Peer]
    
    style A fill:#e1f5ff
    style B fill:#e1ffe1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#e1ffe1
    style F fill:#c2ffc2
    style G fill:#fff5e1
```

### WebSockets

**Description**: Full-duplex communication channel over a single TCP connection.

```mermaid
graph TD
    A[Client] --> B[WebSocket Handshake]
    B --> C[Server]
    C --> D[Connection Established]
    
    D --> E[Bidirectional Communication]
    E --> F[Client → Server]
    E --> G[Server → Client]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#e1ffe1
    style E fill:#e1ffe1
    style F fill:#fff5e1
    style G fill:#fff5e1
```

**WebSocket Lifecycle:**
1. **Handshake**: HTTP upgrade request
2. **Connection**: Persistent TCP connection
3. **Communication**: Bidirectional data exchange
4. **Close**: Connection termination

**Use Cases:**
- Chat applications
- Real-time collaboration
- Live updates
- Gaming

**Example:**
```python
# Server (Python with Flask-SocketIO)
from flask_socketio import SocketIO, emit

socketio = SocketIO(app)

@socketio.on('message')
def handle_message(data):
    emit('message', data, broadcast=True)

# Client (JavaScript)
const socket = io();
socket.emit('message', {text: 'Hello'});
socket.on('message', (data) => {
    console.log(data);
});
```

### Server-Sent Events (SSE)

**Description**: Server pushes data to client over HTTP.

```mermaid
graph TD
    A[Client] --> B[HTTP Request]
    B --> C[Server]
    C --> D[Keep Connection Open]
    D --> E[Push Events]
    E --> F[Client Receives]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#e1ffe1
    style F fill:#e1ffe1
```

**Use Cases:**
- Live feeds
- Stock tickers
- Notifications
- One-way updates

**Example:**
```python
# Server (Flask)
from flask import Response, stream_with_context

@app.route('/stream')
def stream():
    def event_stream():
        while True:
            yield f"data: {get_update()}\n\n"
    return Response(stream_with_context(event_stream()),
                   mimetype='text/event-stream')

# Client (JavaScript)
const eventSource = new EventSource('/stream');
eventSource.onmessage = (event) => {
    console.log(event.data);
};
```

### Real-time System Design

**Architecture Pattern:**
```mermaid
graph TD
    A[Client] --> B[WebSocket Server]
    B --> C[Message Queue]
    C --> D[Worker Services]
    D --> E[Database]
    
    F[Redis Pub/Sub] --> G[Real-time Updates]
    G --> B
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style F fill:#ffd1d1
    style G fill:#ffd1d1
```

**Scaling Considerations:**
- **Connection Management**: Handle many concurrent connections
- **State Management**: Session state and user presence
- **Message Ordering**: Ensure message delivery order
- **Fault Tolerance**: Handle server failures gracefully

## 🔧 Microservices Architecture

### What are Microservices?

Microservices architecture structures an application as a collection of loosely coupled services.

```mermaid
graph TD
    A[API Gateway] --> B[User Service]
    A --> C[Order Service]
    A --> D[Product Service]
    A --> E[Payment Service]
    
    B --> F[User Database]
    C --> G[Order Database]
    D --> H[Product Database]
    E --> I[Payment Database]
    
    J[Service Discovery] --> K[Register Services]
    K --> B
    K --> C
    K --> D
    K --> E
    
    style A fill:#e1f5ff
    style B fill:#e1ffe1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style J fill:#ffd1d1
    style K fill:#ffd1d1
```

### Microservices Communication

**Synchronous (HTTP/REST):**
```python
# Service A calling Service B
import requests

def get_user_orders(user_id):
    response = requests.get(f'http://order-service/orders/{user_id}')
    return response.json()
```

**Asynchronous (Message Queue):**
```python
# Service A publishing event
def publish_order_event(order_data):
    queue.publish('order_events', order_data)

# Service B consuming event
def handle_order_event(event):
    process_order(event.data)
```

**Service Discovery:**
```python
# Service registration
def register_service(service_name, address):
    registry.register(service_name, address)

# Service discovery
def discover_service(service_name):
    return registry.get_address(service_name)
```

### Microservices Patterns

**API Gateway Pattern:**
```mermaid
graph TD
    A[Client] --> B[API Gateway]
    B --> C[Routing]
    B --> D[Authentication]
    B --> E[Rate Limiting]
    
    C --> F[Service A]
    C --> G[Service B]
    C --> H[Service C]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#c2ffc2
    style E fill:#c2ffc2
    style F fill:#e1ffe1
    style G fill:#e1ffe1
    style H fill:#e1ffe1
```

**Circuit Breaker Pattern:**
```python
class CircuitBreaker:
    def __init__(self, failure_threshold=5, timeout=60):
        self.failure_count = 0
        self.failure_threshold = failure_threshold
        self.timeout = timeout
        self.state = 'closed'
        self.last_failure_time = None
    
    def call(self, func):
        if self.state == 'open':
            if time.time() - self.last_failure_time > self.timeout:
                self.state = 'half-open'
            else:
                raise Exception('Circuit breaker is open')
        
        try:
            result = func()
            self.failure_count = 0
            self.state = 'closed'
            return result
        except Exception as e:
            self.failure_count += 1
            self.last_failure_time = time.time()
            if self.failure_count >= self.failure_threshold:
                self.state = 'open'
            raise e
```

## 📊 Event-Driven Architecture

### What is Event-Driven Architecture?

Event-driven architecture uses events to trigger and communicate between decoupled services.

```mermaid
graph TD
    A[Event Producer] --> B[Event Bus]
    B --> C[Event Consumer 1]
    B --> D[Event Consumer 2]
    B --> E[Event Consumer 3]
    
    F[Event Store] --> G[Replay Events]
    G --> C
    G --> D
    G --> E
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#e1ffe1
    style D fill:#c2ffc2
    style E fill:#fff5e1
    style F fill:#f5e1ff
    style G fill:#f5e1ff
```

### Event Sourcing

**Description**: Store state as a sequence of events.

```mermaid
graph TD
    A[Event Stream] --> B[Event 1: User Created]
    B --> C[Event 2: Order Placed]
    C --> D[Event 3: Payment Made]
    D --> E[Event 4: Order Shipped]
    
    F[Replay Events] --> G[Rebuild State]
    
    style A fill:#e1f5ff
    style B fill:#e1ffe1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style F fill:#ffd1d1
    style G fill:#ffd1d1
```

**Advantages:**
- Complete audit trail
- Temporal queries
- Event replay
- Debugging support

**Disadvantages:**
- Complexity
- Event schema evolution
- Need for projections
- Learning curve

### CQRS (Command Query Responsibility Segregation)

**Description**: Separate models for read and write operations.

```mermaid
graph TD
    A[Commands] --> B[Write Model]
    B --> C[Event Store]
    C --> D[Event Bus]
    D --> E[Read Model]
    E --> F[Queries]
    
    style A fill:#e1f5ff
    style B fill:#ffe1e1
    style C fill:#c2ffc2
    style D fill:#fff5e1
    style E fill:#f5e1ff
    style F fill:#f5e1ff
```

**Advantages:**
- Optimized read/write models
- Scalability
- Flexibility
- Performance

**Disadvantages:**
- Complexity
- Eventual consistency
- More code
- Synchronization challenges

## 🎯 Advanced System Design Selection

```mermaid
graph TD
    A[Design Decision] --> B{Need real-time?}
    A --> C{Need async processing?}
    A --> D{Need strong consistency?}
    
    B -->|Yes| E[WebSockets/SSE]
    B -->|No| F[REST API]
    
    C -->|Yes| G[Message Queues]
    C -->|No| H[Synchronous Communication]
    
    D -->|Yes| I[CP Systems]
    D -->|No| J[AP Systems]
    
    style A fill:#e1f5ff
    style E fill:#e1ffe1
    style F fill:#c2ffc2
    style G fill:#e1ffe1
    style H fill:#c2ffc2
    style I fill:#e1ffe1
    style J fill:#c2ffc2
```

## 🧪 Practice Problems

### Distributed Systems (Day 1-2)
1. **Design Distributed Cache**: Implement distributed locking
2. **Design Leader Election**: Implement Raft algorithm
3. **Design Distributed Counter**: Handle concurrency

### Message Queues (Day 3-4)
1. **Design Event Sourcing**: Implement event store
2. **Design Message Broker**: Implement pub/sub
3. **Design Async Processing**: Handle failures

### Real-time Systems (Day 5-6)
1. **Design Chat Application**: Implement WebSockets
2. **Design Live Dashboard**: Implement SSE
3. **Design Multiplayer Game**: Handle real-time updates

## 🎯 Daily Exercises (1-Hour Schedule)

### Day 1: Distributed Systems (10 + 20 + 25 + 5)
**Learning (10 min):**
- Read distributed systems section
- Study consensus algorithms
- Understand distributed locking

**Examples (20 min):**
- Implement simple distributed lock
- Practice leader election
- Understand consistency challenges

**Practice (25 min):**
- Design distributed cache system
- Choose consensus algorithm
- Plan failure handling

**Review (5 min):**
- Review distributed system decisions
- Note consistency trade-offs

### Day 2: Message Queues (10 + 20 + 25 + 5)
**Learning (10 min):**
- Read message queue section
- Study queue patterns
- Understand implementations

**Examples (20 min):**
- Implement point-to-point queue
- Practice pub/sub pattern
- Understand message durability

**Practice (25 min):**
- Design event sourcing system
- Choose queue implementation
- Plan message ordering

**Review (5 min):**
- Review message queue decisions
- Note pattern trade-offs

### Day 3: Real-time Systems (10 + 20 + 25 + 5)
**Learning (10 min):**
- Read real-time systems section
- Study WebSockets and SSE
- Understand scaling challenges

**Examples (20 min):**
- Implement WebSocket server
- Practice SSE implementation
- Understand connection management

**Practice (25 min):**
- Design chat application
- Choose real-time technology
- Plan scaling strategy

**Review (5 min):**
- Review real-time decisions
- Note scaling considerations

### Day 4-6: Mixed Practice (10 + 20 + 25 + 5)
**Learning (10 min):**
- Review all advanced concepts
- Study selection guide
- Understand pattern composition

**Examples (20 min):**
- Practice combining patterns
- Study real-world architectures
- Understand system interactions

**Practice (25 min):**
- Design complete distributed system
- Apply all advanced patterns
- Document trade-offs

**Review (5 min):**
- Review your design decisions
- Note areas that need more practice

## 📋 Weekly Summary

### Week 8 Goals
- [ ] Understand distributed system challenges
- [ ] Implement message queue patterns
- [ ] Design real-time systems
- [ ] Apply microservices patterns
- [ ] Master event-driven architecture

### Week 8 Checklist
- [ ] Completed all daily exercises
- [ ] Can design distributed systems
- [ ] Can implement message queues
- [ ] Can design real-time systems
- [ ] Can apply microservices patterns
- [ ] Ready for practice and integration

## 🚀 Next Steps

After mastering advanced system design:
1. **Move to File 09**: Practice Schedule
2. **Apply these concepts** to comprehensive system design
3. **Practice mock interviews** with system design questions
4. **Review and integrate** all learned concepts

## 💡 Key Takeaways

1. **Distributed systems** require handling network and consistency challenges
2. **Message queues** enable async communication and decoupling
3. **Real-time systems** need careful connection and state management
4. **Microservices** provide scalability but add complexity
5. **Event-driven architecture** enables loose coupling and flexibility

---

**You're now ready for practice and integration!** → [09_PRACTICE_SCHEDULE.md](./09_PRACTICE_SCHEDULE.md)
