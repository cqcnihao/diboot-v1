#!/bin/bash

cd diboot-framework
gitbook build
cd ../diboot-components
gitbook build
cd ../diboot-web
gitbook build
cd ../diboot-rest
gitbook build
cd ../diboot-mobile
gitbook build
cd ../diboot-wechat
gitbook build