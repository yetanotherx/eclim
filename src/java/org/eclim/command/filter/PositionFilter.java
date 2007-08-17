/**
 * Copyright (c) 2005 - 2006
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclim.command.filter;

import java.util.List;

import org.eclim.command.CommandLine;
import org.eclim.command.OutputFilter;

import org.eclim.util.file.Position;

import org.eclim.util.vim.VimUtils;

/**
 * Filter for Position.
 *
 * @author Eric Van Dewoestine (ervandew@yahoo.com)
 * @version $Revision$
 */
public class PositionFilter
  implements OutputFilter<List<Position>>
{
  /**
   * {@inheritDoc}
   */
  public String filter (CommandLine _commandLine, List<Position> _result)
  {
    try{
      StringBuffer buffer = new StringBuffer();
      if(_result != null){
        for(Position result : _result){
          String lineColumn = lineColumn = VimUtils.translateLineColumn(result);

          if(lineColumn != null){
            if(buffer.length() > 0){
              buffer.append('\n');
            }
            buffer.append(result.getFilename())
              .append('|')
              .append(lineColumn)
              .append('|')
              .append(result.getMessage());
          }
        }
      }
      return buffer.toString();
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
}
