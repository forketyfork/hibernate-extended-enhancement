# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a demonstration project for Hibernate Extended Bytecode Enhancement, accompanying the Medium article "Hibernate Extended Bytecode Enhancement". The project shows how Hibernate's bytecode enhancement can automatically detect field changes even when they occur outside of setter methods.

## Build System & Commands

This is a Maven-based Java project using Java 21.

### Essential Commands
- **Build**: `mvn clean compile`
- **Run tests**: `mvn test`  
- **Run single test**: `mvn test -Dtest=ExtendedEnhancementTest`
- **Clean**: `mvn clean`

### Dependencies
- Hibernate Core 6.6.51.Final
- H2 Database 2.4.240 (for testing)
- JUnit Jupiter 6.1.0 (for testing)

The project is pinned to the Hibernate 6.6 line because extended bytecode
enhancement is deprecated in Hibernate 7.x. See `README.md` for the upstream
deprecation note.

## Architecture

### Core Components

The project demonstrates Hibernate's extended enhancement feature through:

1. **Cat.java** (`src/main/java/com/forketyfork/hibernate/Cat.java`): JPA entity with:
   - Standard getter/setter methods
   - `myCustomMethodToChangeData()` that directly modifies the `name` field

2. **SneakyCatService.java** (`src/main/java/com/forketyfork/hibernate/SneakyCatService.java`): Service that directly accesses entity fields bypassing setters via `cat.name = "Buddy"`

3. **ExtendedEnhancementTest.java** (`src/test/java/com/forketyfork/hibernate/ExtendedEnhancementTest.java`): Integration test demonstrating that Hibernate can detect changes made through:
   - Custom methods that modify fields directly
   - External services accessing package-private fields

### Hibernate Enhancement Configuration

The Maven build uses `hibernate-enhance-maven-plugin` with:
- `enableDirtyTracking=true`: Tracks which fields have been modified
- `enableExtendedEnhancement=true`: Detects field changes even when setters aren't used
- Plugin version matches `hibernate-core` (6.6.51.Final)

### Test Database Setup

Tests use H2 in-memory database with Hibernate configured for:
- Auto-create schema (`hibernate.hbm2ddl.auto=create-drop`)
- SQL logging enabled (`hibernate.show_sql=true`)

The test verifies that field modifications are properly persisted regardless of how they're made, demonstrating the power of extended bytecode enhancement.