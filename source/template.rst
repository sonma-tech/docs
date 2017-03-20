.. _template:

模板
=========

.. note::

   注解

.. todo::

   待处理

.. important::

   重要

.. tip::

   小技巧

.. warning::

   警告

.. error::

   错误

.. danger::

   危险

.. hint::

   提示

.. attention::

   注意


.. math::

   (a + b)^2 = a^2 + 2ab + b^2

   (a - b)^2 = a^2 - 2ab + b^2

Lorem ipsum [#f1]_ dolor sit amet ... [#f2]_

.. [#f1] 第一条尾注的文本.
.. [#f2] 第二条尾注的文本.

Lorem ipsum [Ref]_ dolor sit amet.

.. [Ref] 参考文献, 书,URL 等.

.. sidebar:: reStructuredText 导读

   ``toctree`` 是 reStructuredText的 :dfn:`directive` （指令）, 一种用途十分广泛的块标记.  定义了参数、选项及目录.

   *Arguments* 直接在双冒号后面给出指令的名字.  每个指令都有不定个数的参数.

   *Options* 在参数后以"字段列表"的形式给出.  如
   ``maxdepth`` 是 ``toctree`` 指令的选项之一.

   *Content* 具体内容,在选项或参数的后面，隔开一个空行.  每个指令后面都跟着不同作用的内容.

   共同的约定是 **内容与选项一般有相同的缩进** .


.. seealso::

   `GNU tar manual, Basic Tar Format <http://link>`_
      归档文件的文档, 包含 GNU tar 扩展.

.. hlist::
:columns: 5

        * 列表
        * 的子
        * 项会
        * 水平
        * 排列


.. code-block:: python
   :emphasize-lines: 3,5

              def some_function():
                  interesting = False
                  print 'This line is highlighted.'
                  print 'This one is not...'
                  print '...but this one is.'

.. literalinclude:: ../.gitignore


接口文档 :ref:`interface`

术语 :ref:`glossary`


这一行有一个术语 :term:`environment`