# QueueStudent: Asynchronous Waitlist Manager

## Key Features

QueueStudent: Asynchronous Waitlist Manager is a high-fidelity simulation engine designed to model the complex dynamics of academic course registration within a high-concurrency environment. The system orchestrates an asynchronous simulation where hundreds of independent student entities attempt to enroll in limited-capacity courses simultaneously, creating a realistic load on the underlying data structures. To handle resource contention, the platform implements a dynamic waitlist management system that automatically queues students when a course reaches capacity and handles real-time enrollment transitions whenever a seat becomes available due to simulated dropouts. Every state change—from initial enrollment to waitlist promotion—is reflected through real-time MVC updates, providing organisers with a transparent and immediate log of the system's operational throughput and saturation levels.

## Technical Implementation

The system’s technical architecture is built on the Java platform, utilizing the `ExecutorService` with a `CachedThreadPool` to manage the massive influx of concurrent registration tasks efficiently. To ensure thread safety without the performance bottlenecks of heavy manual synchronization, the engine leverages high-performance concurrent collections, including `ConcurrentHashMap` for course registries, `CopyOnWriteArrayList` for student enrollment lists, and `ConcurrentLinkedQueue` for non-blocking waitlist management. The communication between the background simulation threads and the graphical user interface is mediated through `SwingUtilities.invokeLater`, ensuring that all visual updates occur safely on the Event Dispatch Thread (EDT). This combination of asynchronous task execution and thread-safe data structures provides a robust framework for simulating large-scale transactional systems.

## Challenges & Reflection

A primary architectural challenge in this project was managing shared resources among hundreds of threads while maintaining high responsiveness and data integrity. By transitioning from traditional synchronized blocks to modern concurrent collections, the system successfully avoided the typical overhead of lock contention, ensuring that student registration and waitlist polling remained efficient even under heavy load. Implementing the logic for automatic enrollment from waitlists required a sophisticated understanding of atomic operations, as the system had to ensure that a seat vacated by a dropout was immediately and correctly filled by the next valid candidate in the queue. This project served as a definitive exercise in balancing asynchronous performance with strict logical consistency, highlighting the power of non-blocking algorithms in building scalable, real-time management platforms.

## Getting Started

To initialize the QueueStudent simulation on your local machine, ensure the JDK is installed and execute the following commands in your terminal:

```bash
# Navigate to the source root directory
cd src

# Compile the modular registration and GUI components
javac controller/*.java model/*.java view/*.java

# Run the simulation through the main entry point
java controller.MainProgram
```
*Author: Alper Eken Course: Concurrent Programming Semester: Spring 2025*
