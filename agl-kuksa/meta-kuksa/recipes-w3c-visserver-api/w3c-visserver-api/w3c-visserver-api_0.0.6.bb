# ******************************************************************************
# Copyright (c) 2018 Robert Bosch GmbH and others.
#
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/org/documents/epl-2.0/index.php
#
#  Contributors:
#      Robert Bosch GmbH - initial API and functionality
# *****************************************************************************

SUMMARY = "W3C VIS server"
DESCRIPTION = "W3C VIS server implementation with GENIVI-VSS data model"
HOMEPAGE = "https://www.w3.org/TR/vehicle-information-api/"
LICENSE = "EPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c7cc8aa73fb5717f8291fcec5ce9ed6c"

inherit pkgconfig cmake systemd
SRCREV = "${AUTOREV}"

DEPENDS = "boost openssl"

SRC_URI = "git://github.com/rai20/kuksa.invehicle.git;protocol=https;branch=demo"
SRC_URI[sha256sum] = "0bf53c8f9c7306ec3dbc6c4c84335ca7ca758f04f93ec3bbd8e05292b3cc4344"
EXTRA_OECMAKE += "-Dpkg_config_libdir=${libdir} -DCMAKE_BUILD_TYPE=Release"

S = "${WORKDIR}/git/w3c-visserver-api"

do_install_append() {
  install -d ${D}${systemd_system_unitdir}
  install -m 0644 ${S}/systemd/w3c-visserver.service ${D}${systemd_system_unitdir}
}

SYSTEMD_SERVICE_${PN} = "w3c-visserver.service"
