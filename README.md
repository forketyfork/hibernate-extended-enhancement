# hibernate-extended-enhancement

[![Build status](https://github.com/forketyfork/hibernate-extended-enhancement/actions/workflows/build.yml/badge.svg)](https://github.com/forketyfork/hibernate-extended-enhancement/actions/workflows/build.yml)
[![MIT License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/language-Java-orange.svg)](https://www.oracle.com/java/)

Source code for the article [Hibernate Extended Bytecode Enhancement](https://medium.com/@forketyfork/hibernate-extended-bytecode-enhancement-ae73962c9bf4)

## Deprecation notice

Extended bytecode enhancement has been deprecated in Hibernate 7. The
[Hibernate 7.3 User Guide](https://docs.hibernate.org/orm/7.3/userguide/html_single/Hibernate_User_Guide.html#BytecodeEnhancement-dirty-tracking)
states:

> Hibernate's extended bytecode enhancement feature has been deprecated,
> primarily because it relies on assumptions and behaviors that often require
> a broader runtime scope than what Hibernate alone can reliably provide,
> similar to container-based environments such as Quarkus or WildFly.
> Applications which make use of this feature should instead use proper
> object-oriented encapsulation, exposing managed state via getters and
> setters.

This project is therefore pinned to the Hibernate 6.6 line, where extended
enhancement is still fully supported.
